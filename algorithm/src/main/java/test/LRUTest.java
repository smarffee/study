package test;

import java.util.HashMap;
import java.util.Map;

public class LRUTest {

    public static void main(String[] args) {

        LRU lru = new LRU(4);

        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);

        lru.get(2);

        lru.put(4, 4);
        lru.put(5, 5);

        System.out.println(" lru: " + lru);
        lru.get(1);
        System.out.println("get1: " + lru);
        lru.get(2);
        System.out.println("get2: " + lru);
        lru.get(3);
        System.out.println("get3: " + lru);
        lru.get(4);
        System.out.println("get4: " + lru);
        lru.get(5);
        System.out.println("get5: " + lru);
        lru.get(4);
        System.out.println("get4: " + lru);

    }

}

class LRU {
    //容量
    private int capacity;

    private Node first;

    private Node last;

    private Map<Integer, Node> map;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }

        moveToHead(node);

        return node.value;
    }

    /**
     * 将节点添加到头部
     *
     * @param node
     */
    private void moveToHead(Node node) {

        //如果要添加的节点是头节点，则什么也不做
        if (node == first) {
            return;
        } else if (node == last) {
            //如果要添加的节点是尾节点，则倒数第二个节点变成最后一个节点
            last.prev.next = null;
            last = last.prev;
        } else {
            //从链表中删除中间的节点
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        //将节点添加到头部
        node.prev = first.prev;
        node.next = first;
        first.prev = node;
        first = node;
    }

    public void put(int key, int value) {
        Node node = map.get(key);

        if (node == null) {
            //1. 创建新节点
            node = new Node();
            node.key = key;
            node.value = value;

            //2. 如果达到最大容量，删除尾部数据
            if (capacity == map.size()) {
                removeLast();
            }

            //3. 将新节点添加到头部
            addToHead(node);

            //4. 保存到map中，方便快速查找
            map.put(key, node);
        } else {
            node.value = value;
            moveToHead(node);
        }

    }

    private void addToHead(Node node) {
        //如果map是空的，则说明是第一次添加数据
        if (map.isEmpty()) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
    }

    /**
     * 删除链表尾部节点数据
     */
    private void removeLast() {
        map.remove(last.key);

        Node preNode = last.prev;
        if (preNode != null) {
            preNode.next = null;
            last = preNode;
        }
    }


    @Override
    public String toString() {
        if (first == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        Node node = first;
        do {
            sb.append(node.key + "->");
        } while ((node = node.next) != null);

        return sb.toString();
    }
}

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return key == node.key;
    }

    @Override
    public int hashCode() {
        return key;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}


