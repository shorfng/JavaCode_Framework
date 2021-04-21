package com.loto.test;

import com.loto.mapper.IUserMapper;
import com.loto.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-19 16:10</p>
 * <p>PageName：Test_Delete.java</p>
 * Function：
 */

public class Test_Delete {
	private IUserMapper userMapper;

	/**
	 * 根据 id 删除一条用户数据（因为本案例涉及的表字段有外键关联，所以执行会报错）
	 */
	@Test
	public void testAdd() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		userMapper.deleteUser(2);
	}

}
