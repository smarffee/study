package com.lin.study.heap;

/**
 * -Xms20m -Xmx20m
 */
public class HeapDemo1 {

    public static void main(String[] args) {
        System.out.println("heapdemo1 start");

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("heapdemo1 end");
    }

}
