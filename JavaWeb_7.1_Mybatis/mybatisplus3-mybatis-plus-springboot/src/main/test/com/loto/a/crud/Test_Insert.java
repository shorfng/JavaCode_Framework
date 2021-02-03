package com.loto.a.crud;

import com.loto.mapper.UserMapper;
import com.loto.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-01-05 0:03</p>
 * <p>PageName：Test_Insert.java</p>
 * <p>Function：使用 MybatisPlus 进行 insert 插入操作</p>
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_Insert {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("TD");
        user.setAge(20);
        user.setMail("TD@loto.com");

        // 返回值 result 是受影响的数据的行数
        int result = userMapper.insert(user);
        System.out.println("受影响的数据的行数：" + result);

        // id为主键自增
        System.out.println("id值为" + user.getId());
    }
}
