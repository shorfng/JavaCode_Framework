package com.loto;

import com.loto.mapper.UserMapper;
import com.loto.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-19 11:15</p>
 * <p>PageName：Test_Plugin.java</p>
 * Function：MybatisPlus 插件配置 - 测试类
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_Plugin {
	@Autowired
	private UserMapper userMapper;

	/**
	 * 测试 - 执行分析插件
	 */
	@Test
	public void test_sqlExplainInterceptor() {
		User user = new User();
		user.setAge(20);

		// 当执行全表更新时，会抛出异常，有效防止一些误操作
		int result = this.userMapper.update(user, null);
		System.out.println("result = " + result);
	}

	/**
	 * 测试 - 性能分析插件
	 */
	@Test
	public void test_performanceInterceptor() {
		// 如果执行 sql 的时间超过配置的 100ms，则抛出异常
		// Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException:  The SQL execution time is too large, please optimize !
		User user = userMapper.selectById(2L);
		System.out.println(user);
	}

	/**
	 * 测试 - 乐观锁插件
	 */
	@Test
	public void test_optimisticLockerInterceptor() {
		User user = new User();
		user.setAge(31);
		user.setId(2L);
		user.setVersion(1);

		// 修改 version 为1的记录，如果成功则 version + 1，失败的话 result 为0，表示影响的行数为0
		int result = this.userMapper.updateById(user);
		System.out.println("result = " + result);
	}
}
