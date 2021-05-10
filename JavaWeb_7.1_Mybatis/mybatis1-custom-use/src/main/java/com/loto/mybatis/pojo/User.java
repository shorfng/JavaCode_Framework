package com.loto.mybatis.pojo;

import lombok.Data;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 10:47</p>
 * <p>PageName：User.java</p>
 * Function：用户实体类
 */

@Data
public class User {
	private Integer id;
	private String username;
	private String password;
}
