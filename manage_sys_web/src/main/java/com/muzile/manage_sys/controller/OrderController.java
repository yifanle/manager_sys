package com.muzile.manage_sys.controller;

import com.github.pagehelper.PageInfo;
import com.muzile.manage_sys.domain.Orders;
import com.muzile.manage_sys.domain.Product;
import com.muzile.manage_sys.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/findAll")
    public PageInfo findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "5") Integer size){
        PageInfo pageInfo = null;
        try {
            List<Orders> ordersList = orderService.findAll(page,size);
            pageInfo = new PageInfo(ordersList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    @RequestMapping("/findById")
    public Orders findById(Integer id){
        Orders orders = null;
        try {
            orders = orderService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @RequestMapping("/update")
    public void update(@RequestBody Orders orders){
        try {
            orderService.update(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/addOrders")
    public void addOrders(@RequestBody Orders orders){
        try {
            orderService.addOrders(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/addProduct4Orders")
    public String addProduct4Orders(@RequestBody Orders orders){
        String msg = "0";
        try {
            boolean isOk = orderService.addProduct4Orders(orders);
            if(isOk)
                msg = "1";
            else
                msg = "0";
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return msg;
        }
    }

    @RequestMapping("/delById")
    public void delById(Integer id){
        try {
            orderService.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/delProductOfOrders")
    public void delProductOfOrders(@RequestBody Orders orders){
        try {
            orderService.delProductOfOrders(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/searchByOrderNum")
    public PageInfo searchByOrderNum(@RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "5") Integer size,@RequestBody Orders o) {
        PageInfo pageInfo = null;
        try {
            List<Orders> ordersList = orderService.searchByOrderNum(page,size,o.getOrderNum());
            pageInfo = new PageInfo(ordersList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
}
