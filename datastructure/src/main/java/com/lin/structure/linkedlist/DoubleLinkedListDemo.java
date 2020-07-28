package com.lin.structure.linkedlist;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero4);

        doubleLinkedList.list();

        HeroNode2 heroNode = new HeroNode2(2, "小卢", "玉麒麟~~");
        doubleLinkedList.update(heroNode);

        System.out.println("更改之后.....");
        doubleLinkedList.list();

        doubleLinkedList.delete(2);

        System.out.println("删除之后.....");

        doubleLinkedList.list();

    }

}

class DoubleLinkedList {

    private HeroNode2 head = new HeroNode2(0, "", "");

    public void delete(int no) {

        HeroNode2 temp = head.next;
        while (temp != null) {
            if (temp.no == no) {
                temp.pre.next = temp.next;

                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }

                return;
            }

            temp = temp.next;
        }
    }

    public void update(HeroNode2 heroNode) {

        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("没有找到节点. no = " + heroNode.no);
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

    public void addByOrder(HeroNode2 heroNode) {

        HeroNode2 temp = head;
        while (temp != null) {
            if (temp.next == null) {
                temp.next = heroNode;
                heroNode.pre = temp;
                return;
            }

            if (temp.next.no > heroNode.no) {

                heroNode.next = temp.next;
                temp.next.pre = heroNode;

                heroNode.pre = temp;
                temp.next = heroNode;

                return;
            } else if (temp.next.no == heroNode.no) {
                System.out.println("添加的编号已经存在. no = " + heroNode.no);
                return;
            }

            temp = temp.next;
        }
    }

    public void add(HeroNode2 heroNode) {

        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }

        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void list() {

        HeroNode2 temp = head.next;
        if (temp == null) {
            System.out.println("双链表为空....");
            return;
        }

        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

class HeroNode2 {

    int no;

    String name;

    String nickName;

    HeroNode2 pre;

    HeroNode2 next;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
