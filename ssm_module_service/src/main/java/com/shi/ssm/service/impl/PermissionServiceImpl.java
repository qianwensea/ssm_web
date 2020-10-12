package com.shi.ssm.service.impl;

import com.shi.ssm.dao.IPermissionDao;
import com.shi.ssm.domain.Permission;
import com.shi.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-12 10:00
 */
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String permissionId) throws Exception {
        return permissionDao.findById(permissionId);
    }

    @Override
    public void deletePermission(String permissionId) throws Exception {
        //删除role_permission中的内容
        permissionDao.deleteFromRole_PermissionByPermissionId(permissionId);
        //删除permission中的内容
        permissionDao.deleteFromPermissionByPermissionId(permissionId);
    }
}
