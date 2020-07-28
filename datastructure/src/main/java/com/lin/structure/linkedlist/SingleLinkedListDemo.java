package com.lin.structure.linkedlist;

/**
 * 单链表
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero3);

        singleLinkedList.list();

        HeroNode heroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(heroNode);

        System.out.println("更改之后.............");
        singleLinkedList.list();

//        singleLinkedList.delete(1);
//        singleLinkedList.delete(2);
//        singleLinkedList.delete(3);
//        singleLinkedList.delete(4);


//        System.out.println("删除之后..............");
//        singleLinkedList.list();
//        System.out.println(singleLinkedList.size());

        singleLinkedList.revert();
        System.out.println("反转之后................");
        singleLinkedList.list();
    }


}

//定义SingleLinkedList 管理英雄
class SingleLinkedList {

    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到单链表
    //思路，当不考虑编号时
    //1. 找到当前链表的最后节点
    //2. 将最后节点的next 指向新的节点
    public void add (HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表找到最后
        while (true) {
            //找到链表最后
            if (temp.next == null) {
                //将最后节点的next执行新加的heroNode
                temp.next = heroNode;
                return;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
    }

    //根据排名添加
    public void addByOrder(HeroNode heroNode) {

        HeroNode temp = head;

        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no){
                System.out.println("要添加的编号已经存在");
                return;
            }

            temp = temp.next;
        }

        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    /**
     * 链表反转
     */
    public void revert() {

        HeroNode revertHead = new HeroNode(0, "", "");
        HeroNode nextNode;
        HeroNode currentNode = head.next;

        while (currentNode != null) {
            nextNode = currentNode.next;

            currentNode.next = revertHead.next;
            revertHead.next = currentNode;

            currentNode = nextNode;
        }

        head = revertHead;
    }

    public void update(HeroNode heroNode) {

        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;

        while (true) {
            if (temp == null) {
                System.out.println("没有找到编号" + heroNode.no);
                return;
            }

            if (temp.no == heroNode.no) {
                temp.name = heroNode.name;
                temp.nickName = heroNode.nickName;

                return;
            }

            temp = temp.next;
        }
    }

    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head;

        while (true) {
            if (temp.next == null) {
                System.out.println("没有找到编号" + no);
                return;
            }

            if (temp.next.no == no) {
                temp.next = temp.next.next;
                return;
            }

            temp = temp.next;
        }

    }

    public int size() {

        HeroNode temp = head.next;

        int size = 0;

        while (true) {
            if (temp == null) {
                return size;
            }

            size++;

            temp = temp.next;
        }

    }

    public void list() {
        //判断节点是否为空
        if (head.next == null) {
            System.out.println("链表为空。");
            return;
        }

        //头节点不能动, 所以需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }

            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

}

class HeroNode {

    int no;

    String name;

    String nickName;

    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
