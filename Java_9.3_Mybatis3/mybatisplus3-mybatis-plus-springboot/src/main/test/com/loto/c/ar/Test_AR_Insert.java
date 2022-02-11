package com.loto.c.ar;

import com.loto.mapper.UserModelMapper;
import com.loto.pojo.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-05 11:11</p>
 * <p>PageName：Test_AR_Insert.java</p>
 * Function： ActiveRecord 的插入操作
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_AR_Insert {
	@Autowired
	private UserModelMapper userModelMapper;

	@Test
	public void test_ARInsert() {
		UserModel user = new UserModel();
		user.setName("TD");
		user.setAge(18);
		user.setMail("shorfng@126.com");

		// INSERT INTO mp_user ( name, age, email ) VALUES ( ?, ?, ? )
		//boolean insert = user.insert();

		// 根据主键判断，是否有记录，有就更新，没有则插入
		// INSERT INTO mp_user ( name, age, email ) VALUES ( ?, ?, ? )
		boolean insert = user.insertOrUpdate();
		System.out.println(insert);
	}
}
