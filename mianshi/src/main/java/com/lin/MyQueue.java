package com.lin;

public class MyQueue {
    private int capacity = 10;
    private int size = 0;
    private Object[] queue;

    public MyQueue() {
        queue = new Object[capacity];
    }

    public boolean put(Object obj) {
        if (size == capacity) {
            return false;
        }

        for(int i = size; i>0; i--) {
            queue[i] = queue[i-1];
        }

        queue[0] = obj;
        size++;

        return true;
    }

    public Object get() {
        if (size == 0) {
            return null;
        }

        Object value = queue[size-1];
        queue[size-1] = null;
        size--;

        return value;
    }

    public static void main(String[] args) {

        String str1 = "1";
        String str2 = "2";
        String str3 = "3";
        String str4 = "4";
        String str5 = "5";
        String str6 = "6";
        String str7 = "7";
        String str8 = "8";
        String str9 = "9";
        String str10 = "10";
        String str11 = "11";


        MyQueue queue = new MyQueue();
        queue.put(str1);
        queue.put(str2);
        queue.put(str3);
        queue.put(str4);
        queue.put(str5);
        queue.put(str6);
        queue.put(str7);
        queue.put(str8);
        System.out.println(queue.get());
        queue.put(str9);
        queue.put(str10);
        System.out.println(queue.put(str11));

        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());

        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());
    }



}
