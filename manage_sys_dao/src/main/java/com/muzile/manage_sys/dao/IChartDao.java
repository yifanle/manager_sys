package com.muzile.manage_sys.dao;

import com.muzile.manage_sys.domain.Metadata;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IChartDao {

    @Select("SELECT SUM(product_quant) dailyQuant,productName label FROM product_orders po,orders o,product p WHERE po.`order_id` = o.`id` AND po.`product_id` = p.`id` GROUP BY product_id")
    List<Metadata> getBarData() throws Exception;

    @Select("SELECT SUM(order_price) dailyPrice, orderTime  chartTime FROM product_orders po,orders o WHERE po.order_id = o.id GROUP BY DATE_FORMAT(orderTime,\"%Y-%m-%d\")")
    List<Metadata> getAreaData() throws Exception;
}
