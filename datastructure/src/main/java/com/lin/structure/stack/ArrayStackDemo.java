package com.lin.structure.stack;

public class ArrayStackDemo {

    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(6);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        stack.list();

        System.out.println("出栈");
        int value = stack.pop();
        System.out.println(value);
    }

}

class ArrayStack {

    private int capacity;

    private int[] stack;

    private int top = -1;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
    }

    public void push(int num) {
        if (full()) {
            throw new RuntimeException("栈满");
        }
        top++;
        stack[top] = num;
    }

    public int pop() {
        if (empty()) {
            throw new RuntimeException("栈空");
        }

        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (empty()) {
            throw new RuntimeException("栈空");
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    public boolean empty() {
        return top == -1;
    }

    public boolean full() {
        return top == capacity - 1;
    }
}
