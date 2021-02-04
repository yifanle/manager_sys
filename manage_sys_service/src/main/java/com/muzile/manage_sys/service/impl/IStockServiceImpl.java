package com.muzile.manage_sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.muzile.manage_sys.dao.IProductDao;
import com.muzile.manage_sys.dao.IStockDao;
import com.muzile.manage_sys.dao.ISysLogDao;
import com.muzile.manage_sys.domain.Product;
import com.muzile.manage_sys.domain.Stock;
import com.muzile.manage_sys.service.IStockService;
import com.muzile.manage_sys.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class IStockServiceImpl implements IStockService {

    @Autowired
    private IStockDao stockDao;
    @Autowired
    private IProductDao productDao;
    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public List<Stock> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return stockDao.findAll();
    }

    @Override
    public Stock findById(Integer id) throws Exception {
        return stockDao.findById(id);
    }

    @Override
    public List<Stock> searchByProName(Integer page, Integer size, String productName) throws Exception {
        PageHelper.startPage(page,size);
        return stockDao.searchByProName(productName);
    }

    @Override
    public boolean addStock(Stock stock) throws Exception {
        //封装数据
        stock.setExecuteTime(new Date());
        stock.setUsername(getCurrentUser().getUsername());
        //判断是否已存在
        Stock foundStock = stockDao.isExist(stock);
        if(foundStock==null){
            //不重复才存入
            stockDao.addStock(stock);
            return true;
        }
        return false;
    }

    @Override
    public void addQuant(Stock stock) throws Exception {
        //根据id查询
        Stock oldStock = stockDao.findById(stock.getId());
        //封装stock
        oldStock.setProductQuant(oldStock.getProductQuant()+stock.getProductQuant());
        oldStock.setExecuteTime(new Date());
        oldStock.setUsername(getCurrentUser().getUsername());
        //更新product中status
        Integer quantity = oldStock.getProductQuant();
        Integer status;
        if(quantity>0 && quantity<=100){
            status = 2;//预警
        }else if (quantity>100){
            status = 0;//充足
        }else{
            status = 1;//缺货
        }
        //更新status
        Product product = new Product();
        product.setProductStatus(status);
        product.setProductName(oldStock.getProductName());
        product.setProductNum(oldStock.getProductNum());
        productDao.updateStatusByPNameAndPNum(product);
        //更新stock的Quant
        stockDao.update(oldStock);
    }

    @Override
    public void update(Stock stock) throws Exception {
        Integer status;
        //先查出还未修改的stock
        Stock oldStock = stockDao.findById(stock.getId());
        //同步跟新product表
        //先查出product的id
        Product product = new Product();
        product.setProductName(oldStock.getProductName());
        product.setProductNum(oldStock.getProductNum());
        Product oldProduct = productDao.findByProductNameAndNum(product);
        //修改更新product
        Integer quantity = stock.getProductQuant();
        if(quantity>0 && quantity<=100){
            status = 2;//预警
        }else if (quantity>100){
            status = 0;//充足
        }else{
            status = 1;//缺货
        }
        oldProduct.setProductNum(stock.getProductNum());
        oldProduct.setProductName(stock.getProductName());
        oldProduct.setProductPrice(stock.getProductPrice());
        oldProduct.setProductStatus(status);
        productDao.update(oldProduct);
        //封装stock更新stock
        stock.setExecuteTime(new Date());
        stock.setUsername(getCurrentUser().getUsername());
        stockDao.update(stock);
    }

    @Override
    public void delById(Integer id) throws Exception {
        stockDao.delById(id);
    }

    @Override
    public void delStocks(Integer[] ids) throws Exception {
        stockDao.delStocks(ids);
    }

    @Override
    public List<Product> findProductList() throws Exception {
        List<Product> productList = new ArrayList<>();
        List<Stock> stockList = stockDao.findAll();
        for(Stock stock : stockList){
            Product product = new Product();
            Integer status;
            product.setProductName(stock.getProductName());
            product.setProductNum(stock.getProductNum());
            product.setProductPrice(stock.getProductPrice());
            Integer quantity = stock.getProductQuant();
            if(quantity>0 && quantity<=100){
                status = 2;//预警
            }else if (quantity>100){
                status = 0;//充足
            }else{
                status = 1;//缺货
            }
            product.setProductStatus(status);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public boolean subQuant(Product product) throws Exception {
        //1、首先查出旧的信息
        Stock oldStock = stockDao.findByPNameAndPNum(product);
        //2、计算新的信息，根据库存来更新product表及stock表 并且根据库存信息发送email预警
        Integer nowQuantity = oldStock.getProductQuant() - product.getProductQuant();
        Integer status = product.getProductStatus();
        if(nowQuantity<0){
            return false;//库存不足订单量
        }else if (nowQuantity<=100){
            //发送预警email
            String email = sysLogDao.getEmail();
            String msg = "【库存预警】\n您库存中编号为" + product.getProductNum() + "的产品“\n" + product.getProductName() + "”库存仅剩" + nowQuantity + "件，请尽快添加库存。";
            MailUtils.sendMail(msg, email);
            //设置productStatus
            status = 2;
        }
        product.setProductStatus(status);
        //更新product
        productDao.updateStatusByPNameAndPNum(product);
        //更新stock
        oldStock.setProductQuant(nowQuantity);
        stockDao.update(oldStock);
        return true;
    }

    private User getCurrentUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        return (User)context.getAuthentication().getPrincipal();
    }
}
