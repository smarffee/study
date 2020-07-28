package com.lin.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * 1. 验证volatile 的内存可见性
 *   1.1 加入 int number = 0; number变量没有被volatile 修饰，没有可见性
 *   1.2 添加了volatile，可以解决可见性问题
 *
 * 2. 验证volatile 不保证原子性
 *   2.1
 */
public class VolatileDemo {

    public static void main(String[] args) {

        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " is start....");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myData.changeNumber();
            System.out.println(
                    Thread.currentThread().getName() + " change number. number is " + myData.number);

        }, "AAA").start();

        while (myData.number == 0) {

        }

        System.out.println("number is changed by other thread. number is " + myData.number);

    }

}

class MyData {

    volatile int number = 0;

    public void changeNumber() {
        number = 60;
    }

}