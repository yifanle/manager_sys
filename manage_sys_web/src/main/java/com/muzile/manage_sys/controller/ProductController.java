package com.muzile.manage_sys.controller;

import com.github.pagehelper.PageInfo;
import com.muzile.manage_sys.domain.Product;
import com.muzile.manage_sys.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@ResponseBody
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findProductList")
    public List<Product> findProductList(){
        List<Product> products = null;
        try {
            products = productService.findProductList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAll")
    public PageInfo findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "5") Integer size){
        PageInfo pageInfo = null;
        try {
            List<Product> products = productService.findAll(page,size);
            pageInfo = new PageInfo(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    @RequestMapping("/findById")
    public Product findById(Integer id){
        Product foundProduct = null;
        try {
            foundProduct = productService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundProduct;
    }

    @RequestMapping(value = "/addProduct",method = {RequestMethod.POST})
    public void addProduct(@RequestBody Product product) {
        try {
            productService.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/delById")
    public void delById(@RequestBody Product p) {
        try {
            productService.delById(p.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/delProducts")
    public void delProducts(@RequestBody Integer[] ids) {
        try {
            productService.delProducts(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/update")
    public void update(@RequestBody Product product) {
        try {
            productService.update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/searchByProName")
    public PageInfo searchByProName(@RequestParam(name="page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "5") Integer size,@RequestBody Product p) {
        PageInfo pageInfo = null;
        try {
            List<Product> products = productService.searchByProName(page,size,p.getProductName());
            pageInfo = new PageInfo(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
}
