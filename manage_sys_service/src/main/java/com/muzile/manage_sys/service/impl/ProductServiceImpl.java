package com.muzile.manage_sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.muzile.manage_sys.dao.IProductDao;
import com.muzile.manage_sys.domain.Product;
import com.muzile.manage_sys.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public Product findById(Integer id) throws Exception{
        return productDao.findById(id);
    }

    @Override
    public void addProduct(Product product) throws Exception {
        productDao.insert(product);
    }

    @Override
    public void delById(Integer id) throws Exception {
        productDao.delById(id);
    }

    @Override
    public void update(Product product) throws Exception {
        productDao.update(product);
    }

    @Override
    public void delProducts(Integer[] ids) throws Exception {
        productDao.delProducts(ids);
    }

    @Override
    public List<Product> searchByProName(Integer page, Integer size,String productName) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.searchByProName(productName);
    }

    @Override
    public List<Product> findProductList() throws Exception {
        return productDao.findAll();
    }
}
