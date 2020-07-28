package com.lin.study.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeapGCTest {

    byte[] bytes = new byte[new Random().nextInt(1024 * 200)];

    public static void main(String[] args) throws InterruptedException {
        List<HeapGCTest> list = new ArrayList<>();

        while (true) {
            list.add(new HeapGCTest());
            Thread.sleep(10);
        }

    }

}
