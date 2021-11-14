package com.adouer.tree.binarysorttree;

/**
 * 二叉排序树
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        /*
        给你一个数列 (7, 3, 10, 12, 5, 1, 9)，要求能够高效的完成对数据的查询和添加
         */
        BinarySortTree binarySortTree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }
        System.out.println("前序遍历");
        binarySortTree.fixOrder();
    }

}

class BinarySortTree {
    /**
     * 根节点
     */
    private Node root;

    /**
     * 添加方法
     *
     * @param node
     */
    public void add(Node node) {
        if (this.root == null) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }

    public void fixOrder() {
        if (this.root != null) {
            this.root.fixOrder();
        } else {
            System.out.println("空树");
        }
    }
}

/**
 * 节点类
 */
class Node {
    private Integer value;
    private Node left;
    private Node right;

    public Node(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void fixOrder() {
        if (this.left != null) {
            this.left.fixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.fixOrder();
        }

    }

}
