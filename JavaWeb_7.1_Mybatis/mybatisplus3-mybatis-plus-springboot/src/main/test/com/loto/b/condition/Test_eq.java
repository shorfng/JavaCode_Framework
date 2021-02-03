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
 * <p>Date：2021-02-03 15:11</p>
 * <p>PageName：Test_equal.java</p>
 * Function：条件构造器 - 基本比较操作
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_eq {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void test_eq() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();

		// 1、查询 name = Tom 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ?
		//queryWrapper.eq("name", "Tom");

		// 2、查询 name ！= Tom 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name <> ?
		//queryWrapper.ne("name", "Tom");

		// 3、查询 age > 20 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE age > ?
		//queryWrapper.gt("age", 20);

		// 4、查询 age >= 20 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE age >= ?
		//queryWrapper.ge("age", 20);

		// 5、查询 age < 20 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE age < ?
		//queryWrapper.lt("age", 20);

		// 6、查询 age <= 20 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE age <= ?
		//queryWrapper.le("age", 20);

		// 7、查询 age >= 20  且 age <= 28 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE age BETWEEN ? AND ?
		//queryWrapper.between("age", 20, 28);

		// 8、查询 age <= 20  且 age >= 28 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE age NOT BETWEEN ? AND ?
		//queryWrapper.notBetween("age", 20, 28);

		// 9、查询 name 是 xxx 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name IN (?,?)
		//queryWrapper.in("name", "Jone", "Jack");

		// 10、查询 name 不是 xxx 的用户信息
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name NOT IN (?,?)
		//queryWrapper.notIn("name", "Jone", "Jack");

		// 11、多条件综合查询
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE email = ? AND age >= ? AND name IN (?)
		queryWrapper.eq("email", "test4@baomidou.com")
				.ge("age", 21)
				.in("name", "Sandy");

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}

	}
}
