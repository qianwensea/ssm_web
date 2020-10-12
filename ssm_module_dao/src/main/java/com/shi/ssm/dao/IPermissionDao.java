package com.shi.ssm.dao;

import com.shi.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-10 16:24
 */
public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    @Select("select * from permission where id = #{permissionId}")
    Permission findById(String permissionId);

    @Delete("delete from role_permission where permissionId=#{permissionId}")
    void deleteFromRole_PermissionByPermissionId(String permissionId);

    @Delete("delete from permission where id=#{permissionId}")
    void deleteFromPermissionByPermissionId(String permissionId);
}
