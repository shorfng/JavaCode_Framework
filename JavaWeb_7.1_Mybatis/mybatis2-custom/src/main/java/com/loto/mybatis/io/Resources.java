package com.loto.mybatis.io;

import java.io.InputStream;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:10</p>
 * <p>PageName：Resources.java</p>
 * Function：加载/读取配置文件
 */

public class Resources {
	/**
	 * 1、根据配置文件的路径，将配置文件加载成字节输入流，存储在内存中
	 */
	public static InputStream getResourceAsSteam(String path) {
		return Resources.class.getClassLoader().getResourceAsStream(path);
	}
}
