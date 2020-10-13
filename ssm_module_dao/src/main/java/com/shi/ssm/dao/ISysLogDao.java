package com.shi.ssm.dao;

import com.shi.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-12 16:48
 */
public interface ISysLogDao {
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog) throws Exception;

    @Select("select * from syslog")
    List<SysLog> findAll() throws Exception;
}
