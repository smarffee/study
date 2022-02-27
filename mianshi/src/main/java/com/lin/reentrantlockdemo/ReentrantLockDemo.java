package com.lin.reentrantlockdemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t ###### invoked sendEmail()");
    }

    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        reentrantLock.lock();
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
            set();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void set() {
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t ###### invoked set()");
        } finally {
            reentrantLock.unlock();
        }
    }
}

public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendSMS();
        }, "t1").start();

        new Thread(() -> {
            phone.sendSMS();
        }, "t2").start();

        TimeUnit.SECONDS.sleep(2);

        System.out.println();
        System.out.println();

        new Thread(phone, "t3").start();
        new Thread(phone, "t4").start();
    }

}


