package com.lin;

public class ReverseListTest {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        linkedList.print();

        linkedList.reverseList();

        linkedList.print();
    }

}

class LinkedList {

    ListNode root;

    public void reverseList() {
//        root = reverseList1(root);
        root = reverseList2(root);
    }


    public ListNode reverseList2(ListNode root) {

        ListNode current = root;
        //用来记录前一个节点
        ListNode pre = null;
        //用来记录下一个节点
        ListNode next = null;

        while (current != null) {
            //1.保存下一个节点
            next = current.next;
            /*
             * 2.当前节点的下一个节点是上一个节点
             *             pre:null
             *          pre:1->null
             *       pre:2->1->null
             *    pre:3->2->1->null
             * pre:4->3->2->1->null
             */
            current.next = pre;
            //3.保存当前节点
            pre = current;
            //4.指针滚动到下一个节点
            current = next;
        }

        return pre;

    }

    /**
     * 递归方式反转链表
     *
     * @param node
     * @return
     */
    private ListNode reverseList1(ListNode node) {

        if (node == null || node.next == null)
            return node;

        ListNode newRoot = reverseList1(node.next);

        ListNode temp = node.next;
        temp.next = node;
        node.next = null;

        return newRoot;

    }

    public void add(int value) {
        ListNode node = new ListNode(value);

        if (root == null) {
            root = node;
            return;
        }

        ListNode temp = root;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = node;
    }

    public void print() {
        if (root == null) {
            System.out.println("空");
            return;
        }

        ListNode temp = root;
        while (temp != null) {
            System.out.print(temp + "->");
            temp = temp.next;
        }

        System.out.println("");
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
