package com.loto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loto.pojo.User;

import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2020-01-03</p>
 * <p>PageName：UserMapper_MybatisPlus.java</p>
 * <p>Function：映射配置文件 - MybatisPlus</p>
 */

public interface UserMapper_MybatisPlus extends BaseMapper<User> {
    /**
     * 查询所有用户
     */
    public List<User> findAll();

   /**
    * 添加用户
    */
    public int insert(User user);
}
