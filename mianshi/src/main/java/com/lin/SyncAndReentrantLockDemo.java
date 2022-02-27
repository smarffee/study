package com.lin;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按顺序调用，实现 A->B-> 三个线程启动，要求如下：
 * A打印12，B打印1234，C打印123456
 * 紧接着
 * A打印12，B打印1234，C打印123456
 * .......
 * 来3轮
 */

class ShareResource {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    /**
     * 干活的线程号
     */
    private String doWorkThreadName = "A";

    public void print(int printNum) {

        lock.lock();
        String currentThreadName = Thread.currentThread().getName();
        try {
            //1.判断条件
            while (!currentThreadName.equals(doWorkThreadName)) {
                if (currentThreadName.equals("A")) {
                    condition1.await();
                } else if (currentThreadName.equals("B"))  {
                    condition2.await();
                } else if (currentThreadName.equals("C"))  {
                    condition3.await();
                }
            }

            //2.干活
            System.out.print(currentThreadName + ":\t");
            for (int i = 1; i <= printNum; i++) {
                System.out.print(i);
            }
            System.out.println("");

            //3.通知
            if (currentThreadName.equals("A")) {
                doWorkThreadName = "B";
                condition2.signal();
            } else if (currentThreadName.equals("B")) {
                doWorkThreadName = "C";
                condition3.signal();
            } else if (currentThreadName.equals("C")) {
                doWorkThreadName = "A";
                condition1.signal();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();

        int totalPrintNum = 3;

        new Thread(() -> {
            for (int i = 0; i < totalPrintNum; i++) {
                shareResource.print(2);
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < totalPrintNum; i++) {
                shareResource.print(4);
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < totalPrintNum; i++) {
                shareResource.print(6);
            }
        }, "C").start();

    }

}
