package com.adouer.tree.huffman;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 赫夫曼编码
 */
public class HuffmanCode {

    /**
     * 哈弗曼编码map
     */
    private static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();

    public static void main(String[] args) {

        String content = "i like like like java do you like a java";
        //1.根据文本内容封装节点信息
        List<Node> nodes = getNodeList(content);
        //2.用节点信息生成对应的赫夫曼树
        Node root = getHuffmanTree(nodes);
        root.preOrder();
        //3.根据赫夫曼树生成赫夫曼编码
        getCode(root);
        System.out.println("huffmanCodes = " + huffmanCodes);
    }

    public static Map<Byte,String> getCode(Node root){
        StringBuilder stringBuilder = new StringBuilder();
        if (root == null) {
            return null;
        }
        getCode(root.left,"0",stringBuilder);
        getCode(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    /**
     * 根据赫夫曼树生成赫夫曼编码
     *
     * @param node          需要处理的节点
     * @param code          当前节点的路径值 左子节点 0，右子节点 1
     * @param stringBuilder 当前节点的完整路径编码
     */
    public static void getCode(Node node, String code, StringBuilder stringBuilder) {

        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) {
            //节点key为空表示：非叶子结点
            if (node.key == null) {
                //递归处理
                //左递归
                getCode(node.left, "0", stringBuilder1);
                //右递归
                getCode(node.right, "1", stringBuilder1);
            } else {
                huffmanCodes.put(node.key, stringBuilder1.toString());
            }
        }
    }

    /**
     * 根据文本内容封装节点信息
     *
     * @param content
     * @return
     */
    public static List<Node> getNodeList(String content) {
        //将内容转换为字节数组
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        Map<Byte, Integer> map = new HashMap<Byte, Integer>();
        List<Node> nodes = new ArrayList<>();
        //统计每个字符出现的次数作为权值，存map
        for (byte b : bytes) {
            if (!map.containsKey(b)) {
                map.put(b, 1);
            } else {
                map.put(b, map.get(b) + 1);
            }
        }
        //封装成节点
        map.forEach((key, weight) -> {
            nodes.add(new Node(key, weight));
        });
        return nodes;

    }

    /**
     * 根据传入的数组，生成赫夫曼树,返回根节点
     */
    public static Node getHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            //1.从小到大排序
            nodes.sort(Comparator.comparingInt(o -> o.weight));
            /*
             2.取出前两个，作为最小的左子节点和右子节点，计算出父节点·
             */
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //设置父节点
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
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
        private Byte key;
        private Integer weight;

        public Node(Byte value, Integer weight) {
            this.weight = weight;
            this.key = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", weight=" + weight +
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
