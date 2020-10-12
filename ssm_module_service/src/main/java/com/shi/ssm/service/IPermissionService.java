package com.shi.ssm.service;

import com.shi.ssm.domain.Permission;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-12 10:00
 */
public interface IPermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;

    Permission findById(String permissionId) throws Exception;

    void deletePermission(String permissionId) throws Exception;
}
