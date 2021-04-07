package com.loto.mapper;

import com.loto.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-17 10:36</p>
 * <p>PageName：IUserMapper.java</p>
 * Function：
 */

public interface IUserMapper {
	/**
	 * 查询所有用户
	 */
	public List<User> findAll() throws IOException;

	/**
	 * 多条件组合查询：if
	 */
	public List<User> findByCondition(User user);

	/**
	 * 多值查询：foreach
	 */
	public List<User> findByIds(int[] ids);

	/**
	 * 新增
	 */
	public void saveUser(User user);

	/**
	 * 更新
	 */
	public void updateUser(User user);

	/**
	 * 删除
	 */
	public void deleteUser(int id);

	/**
	 * 一对多查询（1个用户多条订单记录）
	 */
	public List<User> findAllUser() throws IOException;

	/**
	 * 多对多查询（查询多个⽤户同时查询出每个⽤户的所有⻆⾊）
	 */
	List<User> findAllUserAndRole();
}
