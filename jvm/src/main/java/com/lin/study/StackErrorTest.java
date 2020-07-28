package com.lin.study;

/**
 * 演示栈中异常：StackOverflowError
 *
 * 默认情况下：count=11407
 * -Xss256K: count=2471
 */
public class StackErrorTest {
    private static int count = 1;

    public static void main(String[] args) {
        System.out.println(count);
        count++;

        main(args);
    }
}
