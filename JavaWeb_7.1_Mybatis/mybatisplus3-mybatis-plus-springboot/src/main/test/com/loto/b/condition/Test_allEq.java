package com.loto.b.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loto.mapper.UserMapper;
import com.loto.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-03 10:47</p>
 * <p>PageName：Test_allEq.java</p>
 * Function：条件构造器 - allEq方法
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_allEq {
	@Autowired
	private UserMapper userMapper;

	/**
	 * allEq(Map<R, V> params) <p>
	 * 参数1：params（key 为数据库字段名, value 为字段值）
	 */
	@Test
	public void test1_allEq() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "jack");
		map.put("age", null);

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ? AND age IS NULL
		queryWrapper.allEq(map);

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * allEq(Map<R, V> params, boolean null2IsNull) <p>
	 * 参数1：params（key 为数据库字段名, value 为字段值）  <p>
	 * 参数2：null2IsNull  <p>
	 * &nbsp&nbsp 为 true 则在 map 的 value 为 null 时调用 isNull 方法 <p>
	 * &nbsp&nbsp 为 false 时则忽略 value 为 null 的
	 */
	@Test
	public void test2_allEq() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "jack");
		map.put("age", null);

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ? AND age IS NULL
		//queryWrapper.allEq(map, true);

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ?
		queryWrapper.allEq(map, false);

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * allEq(boolean condition, Map<R, V> params, boolean null2IsNull) <p>
	 * 参数1：condition（为true，参数3才会起作用  为false，参数3不起作用，且没有where条件） <p>
	 * 参数2：params（key 为数据库字段名, value 为字段值） <p>
	 * 参数3：null2IsNull  <p>
	 * &nbsp&nbsp 为 true 则在 map 的 value 为 null 时调用 isNull 方法 <p>
	 * &nbsp&nbsp 为 false 时则忽略 value 为 null 的
	 */
	@Test
	public void test3_allEq() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "jack");
		map.put("age", null);

		// SELECT id,name,email AS mail,info_mark FROM mp_user
		//queryWrapper.allEq(false, map, false);

		// SELECT id,name,email AS mail,info_mark FROM mp_user
		//queryWrapper.allEq(false, map, true);

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ? AND age IS NULL
		//queryWrapper.allEq(true, map, true);

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ?
		queryWrapper.allEq(true, map, false);

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * allEq(BiPredicate<R, V> filter, Map<R, V> params) <p>
	 * 参数1：filter（过滤函数,是否允许字段传入比对条件中） <p>
	 * 参数2：params（key 为数据库字段名, value 为字段值）
	 */
	@Test
	public void test4_allEq() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "jack");
		map.put("age", null);

		// 1、只在 where 条件后拼接 name 的查询条件
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ?
		//queryWrapper.allEq((k, v) -> k.equals("name"), map);

		// 2、不在 where 条件后拼接 name 的查询条件，其他查询条件根据 map 中的 kv 进行拼接
		// 此处除了name之外，只有age可以拼接
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE age IS NULL
		queryWrapper.allEq((k, v) -> !k.equals("name"), map);

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * allEq(BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull) <p>
	 * 参数1：filter（过滤函数,是否允许字段传入比对条件中） <p>
	 * 参数2：params（key 为数据库字段名, value 为字段值） <p>
	 * 参数3：null2IsNull  <p>
	 * &nbsp&nbsp 为 true 则在 map 的 value 为 null 时调用 isNull 方法 <p>
	 * &nbsp&nbsp 为 false 时则忽略 value 为 null 的
	 */
	@Test
	public void test5_allEq() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "jack");
		map.put("age", null);

		// 1、不管 null2IsNull 的值，只在 where 条件后拼接 name 的查询条件
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ?
		//queryWrapper.allEq((k, v) -> k.equals("name"), map, true);

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ?
		//queryWrapper.allEq((k, v) -> k.equals("name"), map, false);

		// 2、不在 where 条件后拼接 name 的查询条件，其他查询条件根据 map 中的 kv 进行拼接，且根据 null2IsNull 的值进行判断是否拼接条件为NULL的
		// 此处除了name之外，只有age可以拼接
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE age IS NULL
		//queryWrapper.allEq((k, v) -> !k.equals("name"), map, true);

		// SELECT id,name,email AS mail,info_mark FROM mp_user
		queryWrapper.allEq((k, v) -> !k.equals("name"), map, false);

		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}

	/**
	 * allEq(boolean condition, BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull) <p>
	 * 参数1：condition（为true，参数4才会起作用 为false，参数4不起作用，且没有where条件） <p>
	 * 参数2：filter（过滤函数,是否允许字段传入比对条件中） <p>
	 * 参数3：params（key 为数据库字段名, value 为字段值） <p>
	 * 参数4：null2IsNull  <p>
	 * &nbsp&nbsp 为 true 则在 map 的 value 为 null 时调用 isNull 方法 <p>
	 * &nbsp&nbsp 为 false 时则忽略 value 为 null 的
	 */
	@Test
	public void test6_allEq() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "jack");
		map.put("age", null);

		// ===========================  condition为True  ======================================
		// 1、不管 null2IsNull 的值，只在 where 条件后拼接 name 的查询条件
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ?
		//queryWrapper.allEq(true, (k, v) -> k.equals("name"), map, true);

		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE name = ?
		//queryWrapper.allEq(true, (k, v) -> k.equals("name"), map, false);


		// 2、不在 where 条件后拼接 name 的查询条件，其他查询条件根据 map 中的 kv 进行拼接，且根据 null2IsNull 的值进行判断是否拼接条件为NULL的
		// 此处除了name之外，只有age可以拼接
		// SELECT id,name,email AS mail,info_mark FROM mp_user WHERE age IS NULL
		//queryWrapper.allEq(true, (k, v) -> !k.equals("name"), map, true);

		// SELECT id,name,email AS mail,info_mark FROM mp_user
		//queryWrapper.allEq(true, (k, v) -> !k.equals("name"), map, false);
		// ===========================  condition为True  ======================================


		// ===========================  condition为false  ======================================
		// 3、只要参数1 condition 为false，则不管后面的参数值，全都不拼接 where 条件
		// SELECT id,name,email AS mail,info_mark FROM mp_user
		//queryWrapper.allEq(false, (k, v) -> k.equals("name"), map, true);

		// SELECT id,name,email AS mail,info_mark FROM mp_user
		//queryWrapper.allEq(false, (k, v) -> k.equals("name"), map, false);

		// SELECT id,name,email AS mail,info_mark FROM mp_user
		//queryWrapper.allEq(false, (k, v) -> !k.equals("name"), map, true);

		// SELECT id,name,email AS mail,info_mark FROM mp_user
		queryWrapper.allEq(false, (k, v) -> !k.equals("name"), map, false);
		// ===========================  condition为false  ======================================


		List<User> users = userMapper.selectList(queryWrapper);
		for (User user : users) {
			System.out.println(user);
		}
	}

}
