package com.shi.ssm.service.impl;

import com.shi.ssm.domain.Product;
import com.shi.ssm.service.IProductService;
import com.shi.ssm.dao.IProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-09-30 16:10
 */
@Service(value = "productService")
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception{
        List<Product> products = productDao.findAll();
        return products;
    }

    @Override
    public void save(Product product) throws Exception{
        productDao.save(product);
    }
}
