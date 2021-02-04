package com.muzile.manage_sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.muzile.manage_sys.dao.IOrderDao;
import com.muzile.manage_sys.dao.IPOrderDao;
import com.muzile.manage_sys.dao.IStockDao;
import com.muzile.manage_sys.domain.Orders;
import com.muzile.manage_sys.domain.POrder;
import com.muzile.manage_sys.domain.Product;
import com.muzile.manage_sys.service.IOrderService;
import com.muzile.manage_sys.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IPOrderDao pOrderDao;
    @Autowired
    private IStockService stockService;

    @Override
    public List<Orders> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }

    @Override
    public Orders findById(Integer id) throws Exception {
        return orderDao.findById(id);
    }

    @Override
    public void update(Orders orders) throws Exception {
        orderDao.updateOrders(orders);
        if(orders.getProductList()!=null&&orders.getProductList().size()>0){
            List<POrder> pOrderList = new ArrayList<>();
            for (Product product:orders.getProductList()){
                POrder pOrder = new POrder();
                pOrder.setOrder_id(orders.getId());
                pOrder.setProduct_id(product.getId());
                pOrder.setOrder_price(product.getProductQuant()*product.getProductPrice());
                pOrder.setProduct_quant(product.getProductQuant());
                pOrderList.add(pOrder);
            }
            pOrderDao.updatePO(pOrderList);
        }
    }

    @Override
    public void addOrders(Orders orders) throws Exception {
        orders.setOrderTime(new Date());
        orderDao.addOrders(orders);
    }

    @Override
    public boolean addProduct4Orders(Orders orders) throws Exception {
        //封装数据：
        POrder pOrder = new POrder();
        //前端页面设置为一次只可以添加一个product
        Product product = orders.getProductList().get(0);
        //防止NullPointException的错误
        if(product!=null){
            //跟新库存
            boolean isEnough = stockService.subQuant(product);
            if(isEnough){
                pOrder.setOrder_id(orders.getId());
                pOrder.setProduct_id(product.getId());
                pOrder.setProduct_quant(product.getProductQuant());
                pOrder.setOrder_price(product.getProductPrice()*product.getProductQuant());
                //先查询是否存在相同的记录，只是quantity不同
                POrder oldPOrder = pOrderDao.findByOidAndPid(pOrder);
                if(oldPOrder!=null){
                    //将oldPOder中quantity增加新添加的量
                    Integer newQuant = oldPOrder.getProduct_quant()+pOrder.getProduct_quant();
                    Double newPrice = oldPOrder.getOrder_price()+pOrder.getOrder_price();
                    oldPOrder.setProduct_quant(newQuant);
                    oldPOrder.setOrder_price(newPrice);
                    pOrderDao.update(oldPOrder);
                }else{
                    //没有重复项则直接更新product_order表
                    pOrderDao.addProduct4Orders(pOrder);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void delById(Integer id) throws Exception {
        orderDao.deById(id);
    }

    @Override
    public void delProductOfOrders(Orders orders) throws Exception {
        POrder pOrder = new POrder();
        pOrder.setOrder_id(orders.getId());
        pOrder.setProduct_id(orders.getProductList().get(0).getId());
        pOrderDao.delProductOfOrders(pOrder);
    }

    @Override
    public List<Orders> searchByOrderNum(Integer page,Integer size ,String orderNum) throws Exception {
        PageHelper.startPage(page,size);
        return orderDao.searchByOrderNum(orderNum);
    }
}
