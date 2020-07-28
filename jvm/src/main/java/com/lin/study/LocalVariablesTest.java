package com.lin.study;

import java.util.Date;

public class LocalVariablesTest {

    private static int num;

    private int count = 1;

    public static void main(String[] args) {
        LocalVariablesTest test = new LocalVariablesTest();
        int num = 10;
    }

    public LocalVariablesTest() {
        this.count = 1;
    }

    public void method1() {
        Date date = new Date();
        String name = "张三";
        method2(date, name);
        System.out.println(date + name);
    }

    public String method2(Date date, String name) {
        double weight = 65.5;
        char gender = '男';
        return "";
    }

    public void method3() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }

        int c = a + 1;
    }

    /*
        变量的分类：

        按照数据类型分为：
        1、基本数据类型
        2、引用数据类型

        按照在类中声明的位置分为：
        成员变量：在使用前，都经历过默认初始化赋值
            类变量：linking 的prepare阶段：给类变量默认赋值 ---> initial阶段：给类变量显式赋值即静态代码块赋值
            实例变量：随着对象的创建，会在堆空间中分配实例变量空间，并进行默认赋值
        局部变量：在使用前，必须要进行显式赋值的！否则，编译不通过。
     */
    public void method4() {
        int num;
//        System.out.println(num); //错误信息：变量num 未进行初始化
    }

}
