package com.loto.mapper;

import com.loto.pojo.Order;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-07 15:50</p>
 * <p>PageName：IOrderMapper.java</p>
 * Function：
 */

public interface IOrderMapper {
	/**
	 * 一对一查询
	 */
	List<Order> findOrderAndUser();
}
