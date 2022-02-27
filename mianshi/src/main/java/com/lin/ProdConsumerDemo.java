package com.lin;

class Resource {

    private int num;

    public synchronized void increment() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + ":increment....");

        while (num != 0) {
            System.out.println(Thread.currentThread().getName() + ":wait....");
            wait();
            System.out.println(Thread.currentThread().getName() + ":finish waiting");
        }

        num++;

        notifyAll();
        System.out.println(Thread.currentThread().getName() + ":finish increment. num is " + num);
    }

    public synchronized void decrement() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + ":decrement....");

        while (num == 0) {
            System.out.println(Thread.currentThread().getName() + ":wait....");
            wait();
            System.out.println(Thread.currentThread().getName() + ":finish waiting");
        }

        num--;

        notifyAll();
        System.out.println(Thread.currentThread().getName() + ":finish decrement. num is " + num);
    }

}


public class ProdConsumerDemo {

    public static void main(String[] args) {

        Resource resource = new Resource();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    resource.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "p1").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    resource.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "p2").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    resource.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "c1").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    resource.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "c2").start();

    }

}



