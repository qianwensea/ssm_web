package com.shi.ssm.controller;

import com.shi.ssm.domain.Permission;
import com.shi.ssm.domain.Role;
import com.shi.ssm.domain.UserInfo;
import com.shi.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-12 9:41
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String roleId) throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/deleteRole.do")
    public String deleteRole(@RequestParam(name = "id",required = true) String roleId) throws Exception{
        roleService.deleteRole(roleId);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId) throws Exception{
        ModelAndView mv = new ModelAndView();
        //1.根据用户的id查询角色
        Role role = roleService.findById(roleId);
        //2.根据角色查询其他可以添加的权限
        List<Permission> permissions = roleService.findOtherPermissions(roleId);

        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] permissionIds) throws Exception{

        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
