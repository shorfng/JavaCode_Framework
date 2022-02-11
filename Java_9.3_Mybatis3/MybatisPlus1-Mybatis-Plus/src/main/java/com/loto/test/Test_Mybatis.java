package com.loto.test;

import com.loto.mapper.UserMapper_Mybatis;
import com.loto.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2020-01-03</p>
 * <p>PageName：Test_Mybatis.java</p>
 * <p>Function：测试类-Mybatis</p>
 */

public class Test_Mybatis {
    @Test
    public void mybatisTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 使用 Mybatis
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper_Mybatis mapper = sqlSession.getMapper(UserMapper_Mybatis.class);

        List<User> users = mapper.findAll();
        System.out.println("======== findAll 方法 ========");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
