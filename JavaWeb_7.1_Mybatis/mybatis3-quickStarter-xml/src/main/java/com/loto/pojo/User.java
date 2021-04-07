package com.loto.pojo;

import lombok.Data;
import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-17 10:36</p>
 * <p>PageName：User.java</p>
 * Function：
 */

@Data
public class User {
	private Integer id;
	private String username;
	private String password;

	/**
	 * 当前用户具备哪些订单
	 */
	private List<Order> orderList;

	/**
	 * 当前用户具备哪些角色
	 */
	private List<Role> roleList;
}
