package com.loto.logicallydelete.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loto.logicallydelete.pojo.User;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-23 15:31</p>
 * <p>PageName：MyBaseMapper.java</p>
 * Function：通用mapper接口，以后创建其他mapper接口时，继承MyBaseMapper
 */

public interface MyBaseMapper<T> extends BaseMapper<T> {
	/**
	 * 查询所有用户
	 */
	public List<User> findAll();

}
