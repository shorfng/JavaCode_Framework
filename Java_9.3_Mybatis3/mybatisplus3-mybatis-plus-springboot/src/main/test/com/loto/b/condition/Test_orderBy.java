package com.loto.b.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loto.mapper.UserMapper;
import com.loto.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-03 16:21</p>
 * <p>PageName：Test_orderBy.java</p>
 * Function：条件构造器 - 排序
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_orderBy {
	@Autowired
	private UserMapper userMapper;

	/**
	 * orderBy(boolean condition, boolean isAsc, R... columns) <p>
	 * 参数1：condition – 是否开启执行条件 <p>
	 * 参数2：isAsc – 是否是 ASC 排序 <p>
	 * 参数3：columns – 字段数组
	 */
	@Test
	public void test_orderBy() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();

		// 升序
		// SELECT id,name,email AS mail,info_mark FROM mp_user ORDER BY age ASC
		queryWrapper.orderBy(true, true, "age");

		// 降序
		// SELECT id,name,email AS mail,info_mark FROM mp_user ORDER BY age DESC
		//queryWrapper.orderBy(true, false, "age");

		// 参数1 condition 为 false 时，不排序
		// SELECT id,name,email AS mail,info_mark FROM mp_user
		//queryWrapper.orderBy(false, true, "age");

		// 参数1 condition 为 false 时，不排序
		// SELECT id,name,email AS mail,info_mark FROM mp_user
		//queryWrapper.orderBy(false, false, "age");

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * 升序排序
	 */
	@Test
	public void test_orderByAsc() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();

		// SELECT id,name,email AS mail,info_mark FROM mp_user ORDER BY age ASC
		queryWrapper.orderByAsc("age");

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * 降序排序
	 */
	@Test
	public void test_orderByDesc() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();

		// SELECT id,name,email AS mail,info_mark FROM mp_user ORDER BY age DESC
		queryWrapper.orderByDesc("age");

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}
}
