package com.loto.metaobjecthandler;

import com.loto.metaobjecthandler.mapper.UserMapper;
import com.loto.metaobjecthandler.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-26 9:23</p>
 * <p>PageName：Test_MetaObjectHandler.java</p>
 * Function：测试 - 自动填充
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_MetaObjectHandler {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testInsert(){
		User user = new User();
		user.setName("Loto");
		user.setAge(20);
		user.setMail("Loto@126.com");

		// 返回值是影响的行数
		int result = userMapper.insert(user);
		System.out.println(result);

		System.out.println("id值为" + user.getId());
	}
}
