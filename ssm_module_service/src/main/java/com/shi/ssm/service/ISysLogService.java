package com.shi.ssm.service;

import com.shi.ssm.domain.SysLog;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-12 16:41
 */
public interface ISysLogService {

    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;
}
