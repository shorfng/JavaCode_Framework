package com.loto.mybatis.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-01 16:51</p>
 * <p>PageName：User.java</p>
 * Function：
 */

@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
}
