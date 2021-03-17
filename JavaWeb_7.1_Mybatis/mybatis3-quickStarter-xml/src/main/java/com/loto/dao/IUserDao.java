package com.loto.dao;

import com.loto.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-17 10:36</p>
 * <p>PageName：IUserDao.java</p>
 * Function：
 */

public interface IUserDao {
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
}
