package com.muzile.manage_sys.dao;

import com.muzile.manage_sys.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IProductDao {

    @Select("select * from product")
    List<Product> findAll() throws Exception;

    @Select("select * from product where id = #{id}")
    Product findById(Integer id) throws Exception;

    @Insert("insert into product (productNum,productName,productPrice,productDesc,productStatus) values (#{productNum},#{productName},#{productPrice},#{productDesc},#{productStatus})")
    void insert(Product product) throws Exception;

    @Delete("delete from product where id = #{id}")
    void delById(Integer id) throws Exception;

    @Update("update product set productNum = #{productNum},productName = #{productName},productPrice = #{productPrice},productDesc = #{productDesc},productStatus = #{productStatus} where id = #{id}")
    void update(Product product) throws Exception;

    @Delete("<script>delete from product where id in<foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    void delProducts(Integer[] ids) throws Exception;

    @Select("select * from product where productName like '%${value}%'")
    List<Product> searchByProName(String productName) throws Exception;

    @Select("select *,#{id} as order_id from product where id in (select product_id from product_orders where order_id = #{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "productNum",column = "productNum"),
            @Result(property = "productName",column = "productName"),
            @Result(property = "productPrice",column = "productPrice"),
            @Result(property = "productDesc",column = "productDesc"),
            @Result(property = "productStatus",column = "productStatus"),
            @Result(property = "productQuant",column = "{productId = id,orderId = order_id}",one = @One(select="com.muzile.manage_sys.dao.IProductDao.findProductQuant"))
    })
    List<Product> findProductsByOid(Integer id) throws Exception;


    @Select("SELECT product_quant as productQuant FROM product p,product_orders po WHERE p.id = #{productId} AND p.id = po.product_id AND po.order_id = #{orderId}")
    Integer findProductQuant(Integer productId,Integer orderId) throws Exception;

    @Select("select * from product where productName = #{productName} and productNum = #{productNum}")
    Product findByProductNameAndNum(Product product) throws Exception;

    @Update("update product set productStatus = #{productStatus} where productName = #{productName} and productNum = #{productNum}")
    void updateStatusByPNameAndPNum(Product product)  throws Exception;
}
