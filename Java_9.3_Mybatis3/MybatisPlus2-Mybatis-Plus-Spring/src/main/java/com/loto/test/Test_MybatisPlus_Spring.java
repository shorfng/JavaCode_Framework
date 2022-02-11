package com.loto.test;

import com.loto.mapper.UserMapper;
import com.loto.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-01-04 20:50</p>
 * <p>PageName：Test_MybatisPlus_Spring.java</p>
 * <p>Function：测试类</p>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test_MybatisPlus_Spring {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }

        List<User> users2 = userMapper.findAll();
        for (User user : users2) {
            System.out.println(user);
        }

    }
}
