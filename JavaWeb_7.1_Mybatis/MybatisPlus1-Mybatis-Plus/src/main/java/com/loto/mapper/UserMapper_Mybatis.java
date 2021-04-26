package com.loto.mapper;

import com.loto.pojo.User;

import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2020-01-03</p>
 * <p>PageName：UserMapper_Mybatis.java</p>
 * <p>Function：映射配置文件 - Mybatis</p>
 */

public interface UserMapper_Mybatis {
    /**
     * 查询所有用户
     */
    public List<User> findAll();
}
