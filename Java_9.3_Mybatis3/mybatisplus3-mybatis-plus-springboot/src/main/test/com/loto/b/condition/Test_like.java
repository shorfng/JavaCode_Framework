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
 * <p>Date：2021-02-03 15:43</p>
 * <p>PageName：Test_like.java</p>
 * Function：条件构造器 - 模糊查询
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_like {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void test_like() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();

		// 1、查询 name 中包含 e 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name LIKE "%e%"
		//queryWrapper.like("name", "e");

		// 2、查询 name 中不包含 e 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name NOT LIKE "%e%"
		//queryWrapper.notLike("name", "e");

		// 3、查询 name 中以 e 为结尾的用户信息
		//  SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name LIKE "%e"
		//queryWrapper.likeLeft("name", "e");

		// 4、查询 name 中以 e 为开头的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name LIKE "e%"
		queryWrapper.likeRight("name", "e");

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}

	}
}
