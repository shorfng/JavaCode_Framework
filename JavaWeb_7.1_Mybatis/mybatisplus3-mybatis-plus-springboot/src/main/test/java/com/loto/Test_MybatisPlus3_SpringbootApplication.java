package com.loto;

import com.loto.mapper.UserMapper;
import com.loto.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-01-04 23:11</p>
 * <p>PageName：Test_MybatisPlus3_SpringbootApplication.java</p>
 * <p>Function：测试类</p>
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_MybatisPlus3_SpringbootApplication {
    @Autowired
    private UserMapper userMapper;

    /**
     * 使用 MybatisPlus 提供的方法
     * */
    @Test
    public void testSelect() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 注解式：自定义 sql
     * */
    @Test
    public void testFindAll() {
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }
}
