package com.loto.mybatis.test;

import com.loto.mybatis.mapper.IUserMapper;
import com.loto.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Author：蓝田_Loto<p>
 * Date：2021-04-02 16:04<p>
 * PageName：Test_TKMapper_Select.java<p>
 * Function：通用mapper - 选择
 */

public class Test_TKMapper_Select {
	private IUserMapper userMapper;

	/**
	 * 根据 id 查询用户
	 */
	@Test
	public void test_tkmapper_select() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		User user = new User();
		user.setId(1);

		// 1、根据条件查询一条数据
		User user1 = userMapper.selectOne(user);
		System.out.println("根据条件查询一条数据：" + user1);

		// 2、查询全部数据
		List<User> user2 = userMapper.select(null);
		System.out.println("查询全部数据：" + user2);

		// 3、根据主键字段进行查询
		User user3 =userMapper.selectByPrimaryKey(1);
		System.out.println("根据主键字段进行查询：" + user3);

		// 4、根据实体中的属性查询符合条件的数量
		int count = userMapper.selectCount(user);
		System.out.println("根据实体中的属性查询符合条件的数量：" + count);

		// 5、example方法
		Example example = new Example(User.class);
		// SELECT id,username,password FROM User WHERE ( id = ? )
		//example.createCriteria().andEqualTo("id", 3);

		// SELECT id,username,password FROM User WHERE ( username like ? )
		//example.createCriteria().andLike("username", "shorfng");

		// SELECT id,username,password FROM User WHERE ( id = ? and username like ? )
		//example.createCriteria().andEqualTo("id", 4).andLike("username", "shorfng");

		// 自定义查询
		List<User> users5 = userMapper.selectByExample(example);
		System.out.println(users5);
	}

}
