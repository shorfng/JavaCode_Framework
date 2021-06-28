package com.loto.spring.oop;

/**
 * Author：蓝田_Loto<p>
 * Date：2021-06-04 14:17<p>
 * PageName：Dog.java<p>
 * Function：
 */

public class Dog extends Animal {

	public static void main(String[] args) {
		Dog dog = new Dog();

		System.out.println("I am dog");
		dog.eat();
		dog.run();
	}
}
