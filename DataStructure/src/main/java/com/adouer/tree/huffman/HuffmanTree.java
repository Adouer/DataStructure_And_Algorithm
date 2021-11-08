package com.adouer.tree.huffman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 赫夫曼树
 * 将数列｛13，7，8，3，29，6，1｝转换为赫夫曼树
 */
public class HuffmanTree {

    /**
     * 思路：
     * 0.创建树的节点类：需要左子节点、右子节点、权值三个属性
     * 1.将数列存放到数集合中，从小到大排序
     * 2.取出前两个，作为最小的左子节点和右子节点，计算出父节点
     * 3.删除两算过的子节个计点，将父节点放入集合
     * 4.重复2、3
     */
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        //得到赫夫曼树根节点
        Node root = getHuffmanTree(arr);
        //前序遍历根节点
        preOrder(root);

    }

    /**
     * 前序遍历
     */
    public static void preOrder(Node node) {
        //前序遍历根节点
        if (node != null) {
            node.preOrder();
        } else {
            System.out.println("该树为空树");
        }
    }

    /**
     * 根据传入的数组，生成赫夫曼树,返回根节点
     */
    public static Node getHuffmanTree(int[] arr) {
        /*
         1.将数列存放到数集合中，从小到大排序
         */
        List<Node> nodes = new ArrayList<>();

        for (int i : arr) {
            nodes.add(new Node(i));
        }

        while (nodes.size() > 1) {
            //从小到大排序
            nodes.sort(Comparator.comparingInt(o -> o.weight));
            /*
             2.取出前两个，作为最小的左子节点和右子节点，计算出父节点
             */
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //设置父节点
            Node parentNode = new Node(leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            /*
             3.删除两算过的子节个计点，将父节点放入集合
             */
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    /**
     * 节点类
     */
    static class Node {
        private Node left;
        private Node right;
        private Integer weight;

        public Node(Integer weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "weight=" + weight +
                    '}';
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }
    }

}
