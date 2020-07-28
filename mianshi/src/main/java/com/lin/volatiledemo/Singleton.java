package com.lin.volatiledemo;

public class Singleton {

    private static volatile Singleton singleton = null;

    private Singleton() {
        System.out.println(Thread.currentThread().getName() + "  我是构造方法");
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Singleton.getInstance();
            }).start();
        }

    }

}
