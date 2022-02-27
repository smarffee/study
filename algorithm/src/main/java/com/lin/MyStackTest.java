package com.lin;

import java.util.Arrays;

public class MyStackTest {

    public static void main(String[] args) {
        MyStack stack = new MyStack(2);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());

        stack.push(4);
        stack.push(5);

        System.out.println(stack.pop());

        System.out.println(stack);
    }

}

class MyStack {

    private int capacity;

    private int size;

    private Object[] table;

    public MyStack(int capacity) {
        this.capacity = capacity;
        table = new Object[capacity];
    }

    public boolean push(Object obj) {
        if (size == capacity) {
            int oldCapacity = capacity;
            capacity = oldCapacity * 3 / 2;
            Object[] newTable = new Object[capacity];
            System.arraycopy(table, 0, newTable, 0, oldCapacity);
            table = newTable;
        }

        table[size++] = obj;

        return true;
    }

    public Object pop() {
        Object obj = table[size-1];
        table[size-1] = null;
        size--;
        return obj;
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "capacity=" + capacity +
                ", size=" + size +
                ", table=" + Arrays.toString(table) +
                '}';
    }
}


