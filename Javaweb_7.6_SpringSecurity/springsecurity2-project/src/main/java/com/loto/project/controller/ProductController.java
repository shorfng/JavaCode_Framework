package com.loto.project.controller;

import com.loto.project.domain.Product;
import com.loto.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * 商品管理
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 查询所有商品
     *
     * @param model
     * @return
     */
    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<Product> productList = productService.list();
        model.addAttribute("productList", productList);
        return "product_list";
    }


    /**
     * 商品修改页面跳转
     *
     * @return
     */
    @RequestMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "product_update";
    }

    /**
     * 商品添加页面跳转
     *
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        return "product_add";
    }

    /**
     * 商品添加或修改
     *
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(Product product) {
        // 主键不为空执行修改,为空执行保存
        if (product.getId() == null) {
            product.setIsShow(0);//设置是否展示 0 - 默认不展示
            product.setCreateTime(new Date());
        }
        productService.saveOrUpdate(product);
        return "redirect:/product/findAll";
    }

    /**
     * 商品删除
     *
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.removeById(id);
        return "redirect:/product/findAll";
    }

    /**
     * 商品是否显示
     *
     * @return
     */
    @GetMapping("/show/{id}/{isShow}")
    public String show(@PathVariable Integer id, @PathVariable Integer isShow) {
        Product product = productService.getById(id);
        product.setIsShow(isShow);
        productService.updateById(product);
        return "redirect:/product/findAll";
    }

}
