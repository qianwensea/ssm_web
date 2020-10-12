package com.shi.ssm.dao;

import com.shi.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @author 千文sea
 * @create 2020-10-10 10:04
 */
public interface IMemberDao {

    @Select("select * from `member` where id=#{id}")
    Member findById(String id) throws Exception;
}
