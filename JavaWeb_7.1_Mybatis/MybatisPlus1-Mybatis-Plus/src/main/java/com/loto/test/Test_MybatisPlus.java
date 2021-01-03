package com.loto.test;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.loto.mapper.UserMapper_MybatisPlus;
import com.loto.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2020-01-03</p>
 * <p>PageName：Test_MybatisPlus.java</p>
 * <p>Function：测试类-MybatisPlus</p>
 */

public class Test_MybatisPlus {
    @Test
    public void mybatisPlusTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 使用 MybatisPlus
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper_MybatisPlus mapper = sqlSession.getMapper(UserMapper_MybatisPlus.class);

        List<User> users1 = mapper.findAll();
        System.out.println("======== findAll 方法 ========");
        for (User user : users1) {
            System.out.println(user);
        }

        List<User> users2 = mapper.selectList(null);
        System.out.println("======== selectList 方法（MybatisPlus提供） ========");
        for (User user : users2) {
            System.out.println(user);
        }
    }
}
