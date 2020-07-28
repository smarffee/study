package com.lin.spi.impl;

import com.lin.spi.Robot;

/**
 * 大黄蜂
 */
public class Bumblebee implements Robot {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }

}
