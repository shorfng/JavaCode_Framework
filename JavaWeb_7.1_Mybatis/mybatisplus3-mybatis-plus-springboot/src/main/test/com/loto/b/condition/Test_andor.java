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
 * <p>Date：2021-02-04 16:06</p>
 * <p>PageName：Test_andor.java</p>
 * Function：条件构造器 - 逻辑查询（and和or）
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_andor {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void test_and() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE ( name = ? OR age = ? )
		//queryWrapper.and(i -> i.eq("name", "Jack").or().eq("age","20"));

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE ( name = ? AND age = ? )
		//queryWrapper.and(i -> i.eq("name", "Jack").eq("age", "20"));

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ? AND age = ?
		queryWrapper.eq("name", "Jack").eq("age", "20");

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}

	@Test
	public void test_or() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ?
		//queryWrapper.or().eq("name", "Jack");

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ? AND age = ?
		//queryWrapper.or().eq("name", "Jack").eq("age","20");

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE ( name = ? )
		//queryWrapper.or(i -> i.eq("name", "Jack"));

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE ( name = ? AND age = ? )
		//queryWrapper.or(i -> i.eq("name", "Jack").eq("age","20"));

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE ( name = ? OR age = ? )
		//queryWrapper.or(i -> i.eq("name", "Jack").or().eq("age","20"));

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ? OR age = ?
		//queryWrapper.eq("name", "Jack").or().eq("age","20");

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ? AND age = ? OR email = ?
		//queryWrapper.eq("name", "Jack")
		//		.eq("age","20")
		//		.or().eq("email","test2@baomidou.com");

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ? AND ( age = ? OR email = ? )
		queryWrapper.eq("name", "Jack")
				.and(i -> i.eq("age", "20").or().eq("email", "test2@baomidou.com"));

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}
}
