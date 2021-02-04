package com.muzile.manage_sys.domain;

public class POrder {
    private Integer id;
    private Integer order_id;
    private Integer product_id;
    private Integer product_quant;
    private Double order_price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getProduct_quant() {
        return product_quant;
    }

    public void setProduct_quant(Integer product_quant) {
        this.product_quant = product_quant;
    }

    public Double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Double order_price) {
        this.order_price = order_price;
    }
}
