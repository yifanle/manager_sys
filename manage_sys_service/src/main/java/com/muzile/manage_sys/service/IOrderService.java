package com.muzile.manage_sys.service;

import com.muzile.manage_sys.domain.Orders;

import java.util.List;

public interface IOrderService {
    List<Orders> findAll(Integer page,Integer size) throws Exception;

    Orders findById(Integer id) throws Exception;

    void update(Orders orders) throws Exception;

    void addOrders(Orders orders) throws Exception;

    boolean addProduct4Orders(Orders orders) throws Exception;

    void delById(Integer id) throws Exception;

    void delProductOfOrders(Orders orders) throws Exception;

    List<Orders> searchByOrderNum(Integer page,Integer size,String orderNum) throws Exception;
}
