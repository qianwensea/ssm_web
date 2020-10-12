package com.shi.ssm.dao;

import com.shi.ssm.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-10 16:24
 */
public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findByRoleId(String roleId);
}
