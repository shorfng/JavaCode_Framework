package com.loto.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loto.project.domain.User;

public interface UserService extends IService<User> {

    // 根据用户名查询用户
    public User findByUsername(String username);

}
