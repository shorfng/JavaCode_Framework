package com.loto.mybatis.test;

import com.loto.mybatis.dao.IUserDao;
import com.loto.mybatis.io.Resources;
import com.loto.mybatis.pojo.User;
import com.loto.mybatis.sqlsession.SqlSession;
import com.loto.mybatis.sqlsession.SqlSessionFactory;
import com.loto.mybatis.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-07-29 0:42</p>
 * <p>PageName：Test_Dao.java</p>
 * <p>Function：使用 JDK 动态代理 - 最终写法</p>
 */

public class Test_getMapper {

    /**
     * 使用代理
     */
    @Test
    public void test_getMapper() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 代理
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
    }
}
