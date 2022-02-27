package com.lin;

public class MyQueueTest {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(10);

        queue.put("a");
        queue.put("b");
        queue.put("c");
        queue.put("d");

        System.out.println(queue.take());
        System.out.println(queue.take());

        queue.put("e");
        queue.put("f");

        queue.print();

    }

}

class MyQueue {

    private int capacity;

    private int size;

    private Object[] table;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        table = new Object[capacity];
    }

    public boolean put(Object obj) {
        if (size == capacity) {
            return false;
        }

        for (int i = size; i > 0; i--) {
            table[i] = table[i-1];
        }

        table[0] = obj;
        size++;

        return true;
    }

    public Object take() {
        if (size == 0) {
            return null;
        }

        Object obj = table[size - 1];
        table[size - 1] = null;
        size--;

        return obj;
    }

    public void print() {
        if (size == 0) {
            System.out.println("");
        }

        for (int i = 0; i < capacity; i++) {
            System.out.print(i + ":" + table[i] + "; ");
        }

        System.out.println();
    }
}
