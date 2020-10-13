package com.shi.ssm.service.impl;

import com.shi.ssm.dao.ISysLogDao;
import com.shi.ssm.domain.SysLog;
import com.shi.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-12 16:42
 */
@Service("sysLogService")
public  class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
