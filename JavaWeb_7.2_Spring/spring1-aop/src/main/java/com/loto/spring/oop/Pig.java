package com.loto.spring.oop;

/**
 * Author：蓝田_Loto<p>
 * Date：2021-06-04 14:18<p>
 * PageName：Pig.java<p>
 * Function：
 */

public class Pig extends Animal {
	public static void main(String[] args) {
		Pig pig = new Pig();

		System.out.println("I am pig");
		pig.eat();
		pig.run();
	}
}
