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
        long startTime = System.currentTimeMillis();

        // 业务逻辑代码
        System.out.println("I can eat...");

        long endTime = System.currentTimeMillis();

        System.out.println("执行时间" + (endTime - startTime) / 1000f + "s");
    }

    public void run() {
        long startTime = System.currentTimeMillis();

        // 业务逻辑代码
        System.out.println("I can run...");

        long endTime = System.currentTimeMillis();

        System.out.println("执行时间" + (endTime - startTime) / 1000f + "s");
    }
}
