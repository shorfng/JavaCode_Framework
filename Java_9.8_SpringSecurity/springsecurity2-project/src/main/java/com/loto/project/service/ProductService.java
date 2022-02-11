package com.loto.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loto.project.domain.Product;

import java.util.List;

public interface ProductService extends IService<Product> {

    List<Product> findAll();

}
