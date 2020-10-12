package com.shi.ssm.service;

import com.shi.ssm.domain.Role;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-12 9:44
 */
public interface IRoleService {

    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    void deleteRole(String roleId) throws Exception;
}
