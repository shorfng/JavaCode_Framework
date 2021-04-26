package com.loto.mybatis.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loto.mybatis.mapper.IUserMapper;
import com.loto.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Author：蓝田_Loto<p>
 * Date：2021-04-25 10:00<p>
 * PageName：Test_PageHelper.java<p>
 * Function：分页插件
 */

public class Test_PageHelper {
	private IUserMapper userMapper;

	/**
	 * 分页查询<p>
	 * pageNum：当前页<p>
	 * pageSize：每页显示的条数
	 */
	@Test
	public void Test_PageHelper() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		PageHelper.startPage(1, 1);
		List<User> users = userMapper.selectAllUser();
		for (User user : users) {
			System.out.println(user);
		}

		PageInfo<User> pageInfo = new PageInfo<>(users);
		System.out.println("总条数：" + pageInfo.getTotal());
		System.out.println("总页数：" + pageInfo.getPages());
		System.out.println("当前页：" + pageInfo.getPageNum());
		System.out.println("每页显示的条数：" + pageInfo.getPageSize());
	}
}
