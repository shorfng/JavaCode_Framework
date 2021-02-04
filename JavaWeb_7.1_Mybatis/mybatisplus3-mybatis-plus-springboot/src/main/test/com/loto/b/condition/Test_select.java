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
 * <p>Date：2021-02-04 16:41</p>
 * <p>PageName：Test_select.java</p>
 * Function：条件构造器 - select 查询显示结果列
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_select {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void test_select() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();

		// SELECT name FROM mp_user WHERE name = ? OR age = ?
		//queryWrapper.eq("name","Jack").or().eq("age",20).select("name");

		// SELECT name,age FROM mp_user WHERE name = ? OR age = ?
		queryWrapper.eq("name","Jack").or().eq("age",20).select("name","age");

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}
}
