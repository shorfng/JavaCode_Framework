package com.loto.spring.oop;

/**
 * Author：蓝田_Loto<p>
 * Date：2021-06-04 14:18<p>
 * PageName：Horse.java<p>
 * Function：
 */

public class Horse extends Animal {
	public static void main(String[] args) {
		Horse horse = new Horse();

		System.out.println("I am horse");
		horse.eat();
		horse.run();
	}
}
