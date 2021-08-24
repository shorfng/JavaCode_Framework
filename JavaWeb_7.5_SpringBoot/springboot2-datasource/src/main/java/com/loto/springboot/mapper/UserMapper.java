package com.loto.springboot.mapper;

import com.loto.springboot.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    public List<User> findAllUser();
}
