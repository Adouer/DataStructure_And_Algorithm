package com.adouer.tree.huffman;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 赫夫曼编码
 */
public class HuffmanCode {

    /**
     * 哈弗曼编码表map
     */
    private static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();

    public static void main(String[] args) {

        String content = "i like like like java do you like a java";
        //将文本内容进行赫夫曼编码压缩
        byte[] huffmanZipByte = huffmanZip(content);
        System.out.println("赫夫曼编码压缩后 = " + Arrays.toString(huffmanZipByte));


        int a = -28;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(256 | a));
        //压缩后的内容解码
        String decode = decode(huffmanZipByte);
        System.out.println("赫夫曼解码 = " + decode);
    }

    /**
     * 将压缩后的二进制字节数组解码成文本内容
     * [-88...]->"1101...."->[105,32.....]
     * 1.将压缩后的字节数组转换为二进制编码字符串（[-88...]->"1101...."）
     * 2.将二进制编码字符串根据编码表恢复压缩前的数组 "1101...."->[105,32.....]
     * 3.将原数组恢复成文本内容
     *
     * @param huffmanZipByte
     * @return
     */
    private static String decode(byte[] huffmanZipByte) {
        // 1.将压缩后的字节数组转换为二进制编码字符串（[-88...]->"1101...."）
        String s = byteToString(huffmanZipByte);
        System.out.println("s = " + s);
        // 2.将二进制编码字符串根据编码表恢复压缩前的数组 "1101...."->[105,32.....]
        // 2.1赫夫曼编码表key,v反转
        HashMap<String, Byte> stringByteHashMap = new HashMap<String, Byte>();
        ArrayList<Byte> bytesList = new ArrayList<Byte>();
        huffmanCodes.forEach((b, str) -> {
            stringByteHashMap.put(str, b);
        });
        // 2.2扫描二进制编码字符串，翻译成压缩前的数组
        int step = 1;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            String tempStr = s.substring(start, start + step);
            if (stringByteHashMap.containsKey(tempStr)) {
                bytesList.add(stringByteHashMap.get(tempStr));
                start += step;
                step = 0;
            }
            step++;
        }
        System.out.println("bytesList = " + bytesList);
        //2.3将二进制字节数组翻译成文本
        StringBuilder stringBuilder = new StringBuilder();

        Byte[] bytes1 = bytesList.toArray(new Byte[bytesList.size()]);
        byte[] bytes = new byte[bytesList.size()];
        for (int i = 0; i < bytesList.size(); i++) {
            bytes[i] = bytesList.get(i);
        }
        return new String(bytes);
    }

    /**
     * 将压缩后的字节数组转换为二进制编码字符串
     *
     * @param huffmanZipByte
     */
    private static String byteToString(byte[] huffmanZipByte) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanZipByte.length; i++) {
            String byteStr = "";
            if (huffmanZipByte.length - 1 == i) {
                byteStr = Integer.toBinaryString(huffmanZipByte[i]);
                stringBuilder.append(byteStr);

            } else {
                byteStr = Integer.toBinaryString(huffmanZipByte[i] | 256);
                stringBuilder.append(byteStr.substring(byteStr.length() - 8, byteStr.length()));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 赫夫曼编码压缩文本
     *
     * @param content
     * @return
     */
    public static byte[] huffmanZip(String content) {
        //1.根据文本内容封装节点信息
        List<Node> nodes = getNodeList(content);
        //2.用节点信息生成对应的赫夫曼树
        Node root = getHuffmanTree(nodes);
        root.preOrder();
        //3.根据赫夫曼树生成赫夫曼编码表
        getCode(root);
        System.out.println("赫夫曼编码表 = " + huffmanCodes);
        //4.将文本内容转换为赫夫曼编码
        byte[] bytes = zipCode(content, huffmanCodes);
        return bytes;
    }

    /**
     * 压缩
     *
     * @param content      文本内容
     * @param huffmanCodes 赫夫曼编码表
     * @return
     */
    public static byte[] zipCode(String content, Map<Byte, String> huffmanCodes) {
        //1.将文本内容转换为字节数组([105，32,...])
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);//40
        System.out.println("赫夫曼编码压缩前 = " + Arrays.toString(bytes));
        //2.将字节数组转换为赫夫曼编码压缩有的字节数组
        //2.1通过赫夫曼编码表，将字节数组转换为二进制形式的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //2.2将字符串形式的二进制转换为二进制字节数组
        String strCode = stringBuilder.toString();
        System.out.println("赫夫曼编码生成的二进制字符串=" + strCode);
        int strCodeLength = strCode.length();
        int length = strCodeLength % 8 == 0 ? strCodeLength / 8 : strCodeLength / 8 + 1;
        byte[] zipCode = new byte[length];
        int index = 0;
        for (int i = 0; i < strCodeLength; i += 8) {
            zipCode[index] = (byte) Integer.parseInt(strCode.substring(i, i + 8 > strCodeLength ? strCodeLength : i + 8), 2);
            index++;
        }
        return zipCode;

    }

    /**
     * 赫夫曼编码表
     *
     * @param root
     * @return
     */
    public static Map<Byte, String> getCode(Node root) {
        StringBuilder stringBuilder = new StringBuilder();
        if (root == null) {
            return null;
        }
        getCode(root.left, "0", stringBuilder);
        getCode(root.right, "1", stringBuilder);
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
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);    //长度40
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
