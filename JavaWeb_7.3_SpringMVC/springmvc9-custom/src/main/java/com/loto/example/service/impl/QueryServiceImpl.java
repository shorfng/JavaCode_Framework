package com.loto.example.service.impl;

import com.loto.example.service.IQueryService;
import com.loto.mvcframework.annotations.LotoService;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-06 16:49</p>
 * <p>PageName：QueryServiceImpl.java</p>
 * <p>Function：</p>
 */

@LotoService("demoService")
public class QueryServiceImpl implements IQueryService {
    @Override
    public String queryName(String name) {
        System.out.println("service 实现类中的name参数：" + name);
        return name;
    }
}
