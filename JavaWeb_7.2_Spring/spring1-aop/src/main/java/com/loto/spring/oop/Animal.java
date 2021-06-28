package com.loto.spring.oop;

/**
 * Author：蓝田_Loto<p>
 * Date：2021-06-04 14:13<p>
 * PageName：Animal.java<p>
 * Function：
 */

public class Animal {
	/**
	 * 高度
	 */
	private String height;

	/**
	 * 体重
	 */
	private float weight;

	public void eat() {
		// 业务逻辑代码
		System.out.println("I can eat...");
	}

	public void run() {
		// 业务逻辑代码
		System.out.println("I can run...");
	}
}
