package com.loto.springboot.service;


import com.loto.springboot.mapper.ProductMapper;
import com.loto.springboot.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    // 查询Master
    public void findAllProductM() {
        List<Product> allProductM = productMapper.findAllProductM();
        System.out.println(allProductM);
    }

    // 查询Slave
    public void findAllProductS() {
        List<Product> allProductS = productMapper.findAllProductS();
        System.out.println(allProductS);
    }
}
