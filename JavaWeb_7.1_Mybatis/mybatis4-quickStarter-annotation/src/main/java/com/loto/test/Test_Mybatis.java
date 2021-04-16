package com.loto.test;

import com.loto.mapper.IUserMapper;
import com.loto.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.Test;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-02 16:04</p>
 * <p>PageName：Test_Mybatis.java</p>
 * Function：注解式
 */

public class Test_Mybatis {
	@Test
	public void test_selectAll() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
		List<User> allUser = mapper.selectAllUser();
		for (User user : allUser) {
			System.out.println(user);
		}
	}


}
