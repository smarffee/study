package com.lin.study.heap;

import java.lang.ref.SoftReference;

public class ReferenceTest {

    public static void main(String[] args) {

        Object obj = new Object();
        SoftReference softReference = new SoftReference(obj);

        obj = null;

    }

}
