package com.lin.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    public static void main(String[] args) {

//        AtomicInteger atomicInteger = new AtomicInteger(5);
//
//        System.out.println(atomicInteger.compareAndSet(5, 2020) + " current value is " + atomicInteger.get());
//        System.out.println(atomicInteger.compareAndSet(5, 1024) + " current value is " + atomicInteger.get());

        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.getAndIncrement());

    }

}
