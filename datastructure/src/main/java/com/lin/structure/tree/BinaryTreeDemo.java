package com.lin.structure.tree;

public class BinaryTreeDemo {
    /*
          宋江
         /   \
       吴用   卢俊义
               \
               林冲
     */
    public static void main(String[] args) {
        //创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();

        Node node1 = new Node(1, "宋江");
        Node node2 = new Node(2, "吴用");
        Node node3 = new Node(3, "卢俊义");
        Node node4 = new Node(4, "林冲");

        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);

        binaryTree.setRoot(node1);

        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后续遍历");
        binaryTree.postOrder();

        System.out.println("查找");
        Node node = binaryTree.preOrderSearch(4);
        System.out.println(node);

        System.out.println("删除");
        binaryTree.delete(4);
        binaryTree.preOrder();
    }

}

class BinaryTree {
    //二叉树根节点
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }

        root.preOrder();
    }

    //中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }

        root.infixOrder();
    }

    //后续遍历
    public void postOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }

        root.postOrder();
    }

    public Node preOrderSearch(int no) {
        return root.preOrderSearch(no);
    }

    public void delete(int no) {
        if (root.getNo() == no) {
            root = null;
            return;
        }
        root.delete(no);
    }
}

class Node {

    private int no;

    private String name;

    private Node left;

    private Node right;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        //输出父节点
        System.out.println(this);
        //递归遍历左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归遍历右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归遍历左子树
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归遍历右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后续遍历
    public void postOrder() {
        //递归遍历左子树
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归遍历右子树
        if (this.right != null) {
            this.right.postOrder();
        }
        //输入父节点
        System.out.println(this);
    }

    //前序查找
    public Node preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }

        Node result = null;
        if (this.left != null) {
            result = this.left.preOrderSearch(no);
        }
        if (result != null) {
            return result;
        }

        if (this.right != null) {
            result = this.right.preOrderSearch(no);
        }
        if (result != null) {
            return result;
        }

        return result;
    }

    public void delete(int no) {
        if (this.left != null) {
            if (this.left.getNo() == no) {
                this.left = null;
                return;
            }
            this.left.delete(no);
        }
        if (this.right != null) {
            if (this.right.getNo() == no) {
                this.right = null;
                return;
            }
            this.right.delete(no);
        }
    }
}
