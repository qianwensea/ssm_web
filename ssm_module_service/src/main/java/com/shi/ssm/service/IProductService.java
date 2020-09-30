package com.shi.ssm.service;
import com.shi.ssm.domain.Product;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-09-30 16:08
 */
public interface IProductService {

    /**
     * 查询所有商品信息
     * @return
     */
    public List<Product> findAll()throws Exception;
}
