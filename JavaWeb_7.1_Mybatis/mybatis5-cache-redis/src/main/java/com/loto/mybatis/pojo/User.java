package com.loto.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-01 16:51</p>
 * <p>PageName：User.java</p>
 * Function：
 */

@Data
// 因为⼆级缓存数据存储介质多种多样，不⼀定只存在内存中，有可能存在硬盘中，如果要取这个缓存，就需要反序列化
public class User implements Serializable {
	private Integer id;
	private String username;
	private String password;
}
