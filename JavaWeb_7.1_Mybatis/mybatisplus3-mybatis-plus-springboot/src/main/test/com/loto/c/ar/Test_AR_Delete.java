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
 * <p>Date：2021-02-05 11:33</p>
 * <p>PageName：Test_AR_Delete.java</p>
 * Function：ActiveRecord 的删除操作
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_AR_Delete {

	@Autowired
	private UserModelMapper userModelMapper;

	@Test
	public void test_ARDelete() {
		UserModel user = new UserModel();

		// 方式1
		//user.setId(6L);
		//boolean delete = user.deleteById();

		// 方式2
		// DELETE FROM mp_user WHERE id=?
		boolean delete = user.deleteById(6L);
		System.out.println(delete);
	}

}
