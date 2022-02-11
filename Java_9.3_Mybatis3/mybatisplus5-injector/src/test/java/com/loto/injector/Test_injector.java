package com.loto.injector;

import com.loto.injector.mapper.UserMapper;
import com.loto.injector.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-23 15:53</p>
 * <p>PageName：Test_injector.java</p>
 * Function：测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_injector {
	@Autowired
	private UserMapper userMapper;

	/**
	 * 测试 - 自定义扩充方法
	 */
	@Test
	public void testFindAll() {
		List<User> all = userMapper.findAll();
		for (User user : all) {
			System.out.println(user);
		}
	}

}
