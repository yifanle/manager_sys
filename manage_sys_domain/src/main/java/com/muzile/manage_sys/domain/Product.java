package com.muzile.manage_sys.domain;

public class Product {
    private Integer id; //主键
    private String productNum;
    private String productName;
    private double productPrice;
    private String productDesc;
    private Integer productStatus;//状态(0充足 1缺货)
    private String productStatusStr;
    private Integer productQuant; // 商品下单数量

    public Integer getProductQuant() {
        return productQuant;
    }

    public void setProductQuant(Integer productQuant) {
        this.productQuant = productQuant;
    }

    public String getProductStatusStr() {
        if (productStatus==0) {
            productStatusStr = "充足";
        }else if (productStatus==1) {
            productStatusStr = "缺货";
        }else if(productStatus==2){
            productStatusStr = "预警";
        }
        return productStatusStr;
    }

    public void setProductStatusStr(String productStatusStr) {
        this.productStatusStr = productStatusStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productDesc='" + productDesc + '\'' +
                ", productStatus=" + productStatus +
                ", productStatusStr='" + productStatusStr + '\'' +
                ", productQuant=" + productQuant +
                '}';
    }
}
