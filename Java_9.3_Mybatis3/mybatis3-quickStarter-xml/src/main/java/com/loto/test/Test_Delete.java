package com.loto.test;

import com.loto.mapper.IUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-17 15:21</p>
 * <p>PageName：Test_Delete.java</p>
 * Function：删除
 */

public class Test_Delete {
	@Test
	public void test_Delete() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		sqlSession.delete("com.loto.mapper.IUserMapper.deleteUser",3);
		sqlSession.commit();

		sqlSession.close();
	}

	@Test
	public void test_Delete_Mapper() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
		mapper.deleteUser(4);

		sqlSession.commit();
		sqlSession.close();
	}
}
