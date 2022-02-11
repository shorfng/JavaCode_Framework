package com.loto.c.ar;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loto.mapper.UserModelMapper;
import com.loto.pojo.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-05 11:22</p>
 * <p>PageName：Test_AR_Update.java</p>
 * Function：ActiveRecord 的更新操作
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_AR_Update {

	@Autowired
	private UserModelMapper userModelMapper;

	/**
	 * 根据主键更新
	 */
	@Test
	public void test_ARUpdateById() {
		UserModel user = new UserModel();
		UserModel user1 = user.selectById(12L);

		user.setId(5L);
		user.setName("CJ");
		//user.setVersion(user1.getVersion());

		// UPDATE mp_user SET name=? WHERE id=?
		boolean update = user.updateById();
		System.out.println(update);
	}

	/**
	 * 根据条件更新
	 */
	@Test
	public void test_ARUpdateByCondition() {
		UserModel user = new UserModel();
		user.setName("CJ");

		QueryWrapper<UserModel> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", "Billie");

		// UPDATE mp_user SET name=? WHERE name = ?
		boolean update = user.update(queryWrapper);
		System.out.println(update);
	}
}
