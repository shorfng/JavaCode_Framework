package com.loto.mybatis.dao;

import com.loto.mybatis.pojo.User;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 14:39</p>
 * <p>PageName：IUserDao.java<p>
 * Function：
 */

public interface IUserDao {
	//查询所有用户
	public List<User> findAll() throws Exception;

	//根据条件进行用户查询
	public User findByCondition(User user) throws Exception;
}
