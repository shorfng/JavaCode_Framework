package com.loto.springboot.service;

import com.loto.springboot.mapper.UserMapper;
import com.loto.springboot.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUser() {
        List<User> allUser = userMapper.findAllUser();
        logger.info("查询出来的用户信息：" + allUser.toString());
        return allUser;
    }
}
