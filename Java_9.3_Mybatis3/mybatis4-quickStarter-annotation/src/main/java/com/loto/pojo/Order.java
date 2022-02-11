package com.loto.pojo;

import lombok.Data;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-20 14:31</p>
 * <p>PageName：Order.java</p>
 * Function：
 */

@Data
public class Order {
	private Integer id;
	private String orderTime;
	private Double total;

	// 表明该订单属于哪个用户
	private User user;
}
