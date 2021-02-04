package com.muzile.manage_sys.service;

import com.muzile.manage_sys.domain.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll(Integer page,Integer size) throws Exception;

    public Product findById(Integer id) throws Exception;

    void addProduct(Product product) throws Exception;

    void delById(Integer id) throws Exception;

    void update(Product product) throws Exception;

    void delProducts(Integer[] ids) throws Exception;

    List<Product> searchByProName(Integer page,Integer size,String productName) throws Exception;

    List<Product> findProductList() throws Exception;
}
