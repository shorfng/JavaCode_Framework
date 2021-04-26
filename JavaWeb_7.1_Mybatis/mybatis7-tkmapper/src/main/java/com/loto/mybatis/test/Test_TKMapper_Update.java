package com.loto.mybatis.test;

import com.loto.mybatis.mapper.IUserMapper;
import com.loto.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author：蓝田_Loto<p>
 * Date：2021-04-26 11:01<p>
 * PageName：Test_TKMapper_Update.java<p>
 * Function：通用mapper - 更新
 */

public class Test_TKMapper_Update {
	private IUserMapper userMapper;

	/**
	 * 更新
	 */
	@Test
	public void test_tkmapper_update() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		User user = new User();
		user.setId(1);
		user.setUsername("mybatis");

		// 根据主键更新实体全部字段，如果实体类的某一个字段没有set，则为null，且null值会被更新到数据库
		int count = userMapper.updateByPrimaryKey(user);
	}
}
