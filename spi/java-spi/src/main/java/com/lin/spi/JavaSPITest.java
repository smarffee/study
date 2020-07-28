package com.lin.spi;

import java.util.ServiceLoader;

public class JavaSPITest {

    public static void main(String[] args) {

        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java com.lin.spi");

        /*
         * 从测试结果可以看出，
         * 我们的两个实现类被成功的加载，
         * 并输出了相应的内容。
         */
        serviceLoader.forEach(Robot::sayHello);
    }

}
