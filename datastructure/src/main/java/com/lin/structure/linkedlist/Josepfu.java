package com.lin.structure.linkedlist;

/**
 * 约瑟夫问题
 */
public class Josepfu {

    public static void main(String[] args) {

        CircleLinkedList circleLinkedList = new CircleLinkedList();
        for (int i = 1; i <= 8; i++) {
            circleLinkedList.add(i);
        }

        circleLinkedList.list();

        int size = circleLinkedList.size();
        System.out.println(size);

        circleLinkedList.deleteNode(1, 8);
    }

}

class CircleLinkedList {

    private JosepfuNode head;

    private JosepfuNode tail;

    private int size;

    //根据用户输入, 然后节点出圈

    /**
     *
     * @param startNo 从第几个节点开始数
     * @param countNum 数几下
     */
    public void deleteNode(int startNo, int countNum) {

        if (head == null || startNo < 1 || startNo > size) {
            System.out.println("参数有误");
            return;
        }

        //要删除的节点, 初始时是头节点
        JosepfuNode removedNode = head;
        //要删除节点的上一个节点, 初始时是尾节点
        JosepfuNode helper = tail;

        /*
         * 从本节点开始数,
         * 如果数2下, 实际指针移动了1次;
         * 如果数3下, 实际指针移动了2次;
         * 指针移动次数, 比实际要数的次数少1次, 所以要减1
         */
        for (int i = 0; i < startNo - 1; i++) {
            helper = helper.getNext();
            removedNode = removedNode.getNext();
        }

        while (true) {
            //两个指针重合表示只剩下一个节点
            if (helper.equals(removedNode)) {
                break;
            }

            //开始数, 指针移动的次数比数的次数少1, 所以要减1
            for (int i = 0; i < countNum - 1; i++) {
                removedNode = removedNode.getNext();
                helper = helper.getNext();
            }

            //输出要删除的节点的编号
            System.out.printf("删除的数是: %d\n", removedNode.getNo());

            //输出以后, 开始删除节点
            removedNode = removedNode.getNext();
            helper.setNext(removedNode);
        }

        //输出最后一个节点
        System.out.printf("删除的数是: %d\n", removedNode.getNo());
    }

    public void add(int num) {

        JosepfuNode node = new JosepfuNode(num);

        if (head == null) {
            head = node;
            tail = node;
            head.setNext(node);
            size = 1;
            return;
        }

        tail.setNext(node);
        node.setNext(head);
        tail = node;

        size++;
    }

    public void list() {

        if (head == null) {
            System.out.println("环形队列为空");
            return;
        }

        JosepfuNode temp = head;
        while (true) {
            System.out.printf("当前编号: %d\n", temp.getNo());
            if (temp.getNext().equals(head)) {
                break;
            }
            temp = temp.getNext();
        }
    }

    public int size() {
        return size;
    }

}

class JosepfuNode {

    private int no;

    private JosepfuNode next;

    public JosepfuNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public JosepfuNode getNext() {
        return next;
    }

    public void setNext(JosepfuNode next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JosepfuNode that = (JosepfuNode) o;

        return no == that.no;
    }

    @Override
    public int hashCode() {
        return no;
    }

    @Override
    public String toString() {
        return "JosepfuNode{" +
                "no=" + no +
                '}';
    }
}
