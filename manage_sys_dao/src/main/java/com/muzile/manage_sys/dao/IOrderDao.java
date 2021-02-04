package com.muzile.manage_sys.dao;

import com.muzile.manage_sys.domain.Member;
import com.muzile.manage_sys.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "totalPrice",column = "id",javaType = Integer.class,one = @One(select="com.muzile.manage_sys.dao.IOrderDao.findOrderPriceById"))
    })
    public List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "totalPrice",column = "id",javaType = Integer.class,one = @One(select="com.muzile.manage_sys.dao.IOrderDao.findOrderPriceById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.muzile.manage_sys.dao.IMemberDao.findById")),
            @Result(property = "productList",column = "id",javaType = java.util.List.class,many = @Many(select = "com.muzile.manage_sys.dao.IProductDao.findProductsByOid")),

    })
    Orders findById(Integer id) throws Exception;

    @Select("SELECT CAST(SUM(order_price) AS DECIMAL(18,2)) totalPrice FROM product_orders WHERE order_id = #{id}")
    Double findOrderPriceById(Integer id) throws Exception;

    @Update("update orders set orderNum = #{orderNum},orderTime = #{orderTime},payType=#{payType},orderStatus=#{orderStatus} where id = #{id}")
    void updateOrders(Orders orders) throws Exception;

    @Insert("insert into orders (orderNum,orderTime,orderDesc,payType,orderStatus,memberId) values (#{orderNum},#{orderTime},#{orderDesc},#{payType},#{orderStatus},#{member.id})")
    void addOrders(Orders orders) throws Exception;

    @Delete("delete from orders where id =#{id}")
    void deById(Integer id) throws Exception;

    @Select("select * from orders where OrderNum like '%${value}%'")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "totalPrice",column = "id",javaType = Integer.class,one = @One(select="com.muzile.manage_sys.dao.IOrderDao.findOrderPriceById"))
    })
    List<Orders> searchByOrderNum(String orderNum) throws Exception;;
}
