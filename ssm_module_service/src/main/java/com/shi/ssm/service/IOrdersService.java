package com.shi.ssm.service;

import com.shi.ssm.domain.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-04 15:56
 */
public interface IOrdersService {

    /**
     * 查询所有订单
     * @return
     */
    List<Orders> findAll(int page,int size) throws Exception;

    /**
     * 根据id查询订单
     * @param orderId
     * @return
     */
    Orders findById(String orderId) throws Exception;
}
