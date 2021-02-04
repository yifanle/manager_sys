package com.muzile.manage_sys.controller;

import com.github.pagehelper.PageInfo;
import com.muzile.manage_sys.domain.Product;
import com.muzile.manage_sys.domain.Stock;
import com.muzile.manage_sys.domain.SysLog;
import com.muzile.manage_sys.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private IStockService stockService;

    @RequestMapping("/findProductList")
    public List<Product> findProductList(){
        List<Product> productList = null;
        try {
            productList = stockService.findProductList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    @RequestMapping("/delStocks")
    public void delStocks(@RequestBody Integer[] ids) {
        try {
            stockService.delStocks(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/delById")
    public void delById(@RequestBody Stock stock) {
        try {
            stockService.delById(stock.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/update")
    public void update(@RequestBody Stock stock) {
        try {
            stockService.update(stock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/addQuant")
    public void addQuant(@RequestBody Stock stock) {
        try {
            stockService.addQuant(stock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addStock",method = {RequestMethod.POST})
    public String addStock(@RequestBody Stock stock) {
        String msg = "0";//添加失败
        try {
            boolean isOk = stockService.addStock(stock);
            if(isOk){
                msg = "1";//添加成功
            }else {
                msg = "2";//添加失败:已存在相同编号或名称的商品
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return msg;
        }
    }

    @RequestMapping("/findAll")
    public PageInfo findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "5") Integer size){
        PageInfo pageInfo = null;
        try {
            List<Stock> stockList = stockService.findAll(page,size);
            pageInfo = new PageInfo(stockList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    @RequestMapping("/findById")
    public Stock findById(Integer id){
        Stock foundStock = null;
        try {
            foundStock = stockService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundStock;
    }

    @RequestMapping("/searchByProName")
    public PageInfo searchByProName(@RequestParam(name="page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "5") Integer size,@RequestBody Stock s) {
        PageInfo pageInfo = null;
        try {
            List<Stock> stockList = stockService.searchByProName(page,size,s.getProductName());
            pageInfo = new PageInfo(stockList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
}
