package com.muzile.manage_sys.dao;

import com.muzile.manage_sys.domain.Orders;
import com.muzile.manage_sys.domain.POrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPOrderDao {

    @Update("<script><foreach collection='list' item='pOrder' separator=';'>update product_orders set product_quant = #{pOrder.product_quant},order_price = #{pOrder.order_price} where order_id = #{pOrder.order_id} and product_id = #{pOrder.product_id}</foreach></script>")
    public void updatePO(@Param("list") List<POrder> pOrderList) throws Exception;

    @Insert("insert into product_orders (order_id,product_id,product_quant,order_price) values (#{order_id},#{product_id},#{product_quant},#{order_price})")
    void addProduct4Orders(POrder pOrder) throws Exception;

    @Select("select * from product_orders where order_id = #{order_id} and product_id = #{product_id}")
    POrder findByOidAndPid(POrder pOrder) throws Exception;

    @Update("update product_orders set product_quant = #{product_quant},order_price = #{order_price} where product_id = #{product_id} and order_id =#{order_id} ")
    void update(POrder oldPOrder) throws Exception;

    @Delete("delete from product_orders where product_id = #{product_id} and order_id=#{order_id}")
    void delProductOfOrders(POrder pOrder) throws Exception;
}
