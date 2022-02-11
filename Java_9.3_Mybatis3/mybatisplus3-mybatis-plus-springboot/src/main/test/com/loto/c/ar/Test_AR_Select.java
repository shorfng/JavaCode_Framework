package com.loto.c.ar;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loto.mapper.UserModelMapper;
import com.loto.pojo.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-05 9:19</p>
 * <p>PageName：Test_AR_Select.java</p>
 * Function： ActiveRecord 的查询操作
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_AR_Select {
	@Autowired
	private UserModelMapper userModelMapper;

	/**
	 * 根据主键查询
	 */
	@Test
	public void test_ARSelectById() {
		UserModel user = new UserModel();
		user.setId(2L);

		// SELECT id,name,age,email AS mail,info_mark FROM mp_user WHERE id=?
		UserModel user1 = user.selectById();
		System.out.println(user1);

	}

	/**
	 * 根据条件查询
	 */
	@Test
	public void test_ARSelectByCondition() {
		QueryWrapper<UserModel> queryWrapper = new QueryWrapper<>();
		queryWrapper.ge("age", "20");

		//  SELECT id,name,age,email AS mail,info_mark FROM mp_user WHERE age >= ?
		UserModel user = new UserModel();
		List<UserModel> users = user.selectList(queryWrapper);

		for (UserModel user1 : users) {
			System.out.println(user1);
		}
	}
}
