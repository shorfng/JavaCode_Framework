package com.loto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loto.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-01-04 20:45</p>
 * <p>PageName：UserMapper.java</p>
 * <p>Function：映射配置文件</p>
 */

public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询所有用户
     */
    @Select("SELECT * FROM mp_user")
    public List<User> findAll();
}
