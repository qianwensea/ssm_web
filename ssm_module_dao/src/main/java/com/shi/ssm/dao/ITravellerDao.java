package com.shi.ssm.dao;

import com.shi.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 千文sea
 * @create 2020-10-10 10:08
 */
public interface ITravellerDao {

    /**
     * 根据订单id查询
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{ordersId})")
    List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
