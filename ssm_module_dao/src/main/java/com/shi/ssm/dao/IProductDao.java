package com.shi.ssm.dao;

import com.shi.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-09-30 16:06
 */
@Repository(value = "productDao")
public interface IProductDao {

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id) throws Exception;

    /**
     * 查询所有的商品信息
     * @return
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * 保存产品
     * @param product
     */
    @Insert("insert into product(productNum,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
}
