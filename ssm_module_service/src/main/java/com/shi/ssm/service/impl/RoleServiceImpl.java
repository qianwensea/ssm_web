package com.shi.ssm.service.impl;

import com.shi.ssm.dao.IRoleDao;
import com.shi.ssm.domain.Permission;
import com.shi.ssm.domain.Role;
import com.shi.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-12 9:44
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public void deleteRole(String roleId) throws Exception {
        //从users_role表中删除
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        //从role_permission中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从role中删除
        roleDao.deleteFromRoleByRoleId(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) throws Exception {
        return roleDao.findOtherPermissions(roleId);
    }
}
