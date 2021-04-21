package com.loto.mapper;

import com.loto.pojo.Order;
import com.loto.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-20 14:37</p>
 * <p>PageName：IOrderMapper.java</p>
 * Function：
 */

public interface IOrderMapper extends Mapper<Order> {
	/**
	 * 一对一（查询订单的同时还查询该订单所属的用户）
	 */
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "orderTime", column = "orderTime"),
			@Result(property = "total", column = "total"),
			@Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "com.loto.mapper.IUserMapper.findUserById"))
	})
	@Select("select * from orders")
	public List<Order> findOrderAndUser();

	/**
	 * 根据 id 查询订单信息
	 */
	@Select("select * from orders where uid = #{uid}")
	public List<Order> findOrderByUid(Integer uid);

}
