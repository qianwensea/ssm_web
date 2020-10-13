package com.shi.ssm.controller;

import com.shi.ssm.domain.Role;
import com.shi.ssm.domain.UserInfo;
import com.shi.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Name;
import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-10 15:12
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }


    @RequestMapping("/save.do")
    @PreAuthorize("hasRole('ADMIN')")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String userId) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception{
        ModelAndView mv = new ModelAndView();
        //1.根据用户的id查询用户
        UserInfo userInfo = userService.findById(userId);
        //2.根据用户查询其他可以添加的角色
        List<Role> roles = userService.findOtherRoles(userId);

        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds) throws Exception{

        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
