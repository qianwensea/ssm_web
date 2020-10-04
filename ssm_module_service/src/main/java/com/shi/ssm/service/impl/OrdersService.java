package com.shi.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.shi.ssm.dao.IOrdersDao;
import com.shi.ssm.domain.Orders;
import com.shi.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-04 15:57
 */
@Service("ordersService")
@Transactional
public class OrdersService implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception{
        //参数pageNum是页码值,pageSize是每页显示多少条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }
}
