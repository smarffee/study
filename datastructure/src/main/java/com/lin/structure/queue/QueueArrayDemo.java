package com.lin.structure.queue;

/**
 * 使用数组模拟队列
 */
public class QueueArrayDemo {

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue(10);

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        queue.get();
        queue.add(6);

        queue.showItem();
        System.out.println("head is " + queue.headItem());
    }

}


class ArrayQueue<E> {

    //队列容量
    private int capacity;
    //头指针(不包含数据)
    private int head = -1;
    //尾指针(包含数据)
    private int tail = -1;
    //队列元素个数
    private int size = 0;

    private Object[] table;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.table = new Object[capacity];
    }

    //判断队列是否满
    public boolean full() {
        return tail == capacity - 1;
    }

    //判断队列是否为空
    public boolean empty() {
        return head == tail;
    }

    //添加数据到队列, 入队
    public void add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }

        if (full()) {
            throw new RuntimeException("queue is full");
        }

        tail++;
        table[tail] = e;
        size++;
    }

    //获取队列数据, 出队
    public E get() {
        if (empty()) {
            throw new RuntimeException("queue is empty");
        }

        head++;
        return (E)table[head];
    }

    //显示队列的头数据, 注意不是取数据
    public E headItem() {
        if (empty()) {
            throw new RuntimeException("queue is empty");
        }
        return (E) table[head + 1];
    }

    public int size() {
        return size;
    }

    public void showItem() {
        if (empty()) {
            System.out.println("queue is empty");
        }

        for (int i = head + 1; i <= tail; i++) {
            System.out.println(table[i]);
        }
    }

}

