package com.loto.springboot.mapper;

import com.loto.springboot.pojo.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    @Select("select * from product")
    public List<Product> findAllProductM();

    @Select("select * from product")
    public List<Product> findAllProductS();
}
