package com.shi.ssm.dao;

import com.shi.ssm.domain.Product;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-09-30 16:06
 */
public interface IProductDao {

    /**
     * 查询所有的商品信息
     * @return
     */
    public List<Product> findAll() throws Exception;
}
