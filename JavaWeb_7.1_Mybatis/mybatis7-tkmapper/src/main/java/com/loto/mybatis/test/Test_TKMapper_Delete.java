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
 * PageName：Test_TKMapper_Delete.java<p>
 * Function：通用mapper - 删除
 */

public class Test_TKMapper_Delete {
	private IUserMapper userMapper;

	/**
	 * 删除
	 */
	@Test
	public void test_tkmapper_delete() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		User user = new User();
		user.setUsername("tkmapper_insert");

		// 根据实体属性作为条件进行删除，查询条件使用等号
		int delete = userMapper.delete(user);

		// 根据主键字段进行删除，方法参数必须包含完整的主键属性
		userMapper.deleteByPrimaryKey(3);
	}
}
