package com.shi.ssm.controller;

import com.shi.ssm.domain.SysLog;
import com.shi.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author 千文sea
 * @create 2020-10-12 15:56
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;//通过配置web.xml中的监听器来注入
    @Autowired
    private ISysLogService sysLogService;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date visitTime;//访问的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;//结束访问的时间
    private Class clazz;//访问的类
    private Method method;//访问的方法
    private String url;//访问的地址

    //前置通知 主要是获取开始时间,执行的是哪一个类的哪一个方法
    @Before("execution(* com.shi.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date(); //当前时间就是访问时间
        clazz = jp.getTarget().getClass(); //具体要访问的类
        String methodName = jp.getSignature().getName();//获取访问方法的名称
        Object[] args = jp.getArgs();
//        获取具体执行的method对象
        if ( args == null || args.length == 0 ){
            method = clazz.getMethod(methodName);
        }else{
            Class[] classArgs = new Class[args.length];
            for (int i = 0;i < classArgs.length;i++){
                classArgs[i] = args[i].getClass();
            }
           method = clazz.getMethod(methodName, classArgs);
        }
    }
    //后置通知
    @After("execution(* com.shi.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        endTime = new Date();
        Long time = endTime.getTime() - visitTime.getTime();//获取访问的时长

        //获取url
        if (clazz != null && method != null && clazz != LogAop.class){
            //1,获取访问类上的注解
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null){
                String[] classValue = clazzAnnotation.value();

                //2.获取方法上的注解
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
            }
        }
        //获取访问的ip
        String ip = request.getRemoteAddr();
        //获取当前操作者
        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //将日志相关信息封装到sysLog中
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);
        sysLog.setMethod("[类名]: " + clazz.getName() + ",[方法名]: " + method.getName());

        //调用service保存日志
        sysLogService.save(sysLog);
    }
}
