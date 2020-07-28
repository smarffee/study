package com.lin.study.heap;

/**
 * -Xms10m -Xmx10m
 */
public class HeapDemo {

    public static void main(String[] args) {
        System.out.println("heapdemo start");

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("heapdemo end");
    }

}
