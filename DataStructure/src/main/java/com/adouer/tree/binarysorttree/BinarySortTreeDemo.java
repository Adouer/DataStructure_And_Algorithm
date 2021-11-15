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
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }
        System.out.println("前序遍历");
        binarySortTree.fixOrder();
        /*
        删除指定节点
         */
        binarySortTree.delete(10);
        System.out.println("删除后");
        binarySortTree.fixOrder();

    }


}

/**
 * 二叉排序树
 */
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

    /**
     * 前序遍历
     */
    public void fixOrder() {
        if (this.root != null) {
            this.root.fixOrder();
        } else {
            System.out.println("空树");
        }
    }

    /**
     * 查找指定节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (this.root == null) {
            System.out.println("空树");
            return null;
        } else {
            return this.root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (this.root == null) {
            System.out.println("空树");
            return null;
        } else {
            return this.root.searchParent(value);
        }
    }

    /**
     * 返回以当前节点为根节点下的最小节点
     *
     * @param node
     * @return
     */
    public int deleteMinRight(Node node) {
        Node temp = node;
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        delete(temp.getValue());
        return temp.getValue();
    }

    /**
     * 删除指定节点
     *
     * @param value
     */
    public void delete(int value) {
        if (this.root == null) {
            System.out.println("空树");
            return;
        }
        //查找目标节点
        Node targetNode = search(value);
        //查找目标节点父节点
        Node parentNode = searchParent(value);
        //找不到指定节点
        if (targetNode == null) {
            System.out.println("删除的节不存在");
            return;
        }
        //能找到指定节点，但是找不到父节点【证明找的节点是根节点】
        /*if (parentNode == null) {

        }*/

        //情况一：如果删除的是叶子节点
        if (targetNode.getLeft() == null && targetNode.getRight() == null) {
            //判断targetNode是父节点的左子节点还是右子节点
            if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                parentNode.setLeft(null);
            }
            if (parentNode.getRight() != null && parentNode.getRight().getValue() == value) {
                parentNode.setRight(null);
            }
            //情况三：如果删除的节点有两个子节点
        } else if (targetNode.getLeft() != null && targetNode.getRight() != null) {  // 有两个子树的节点
            int minRight = deleteMinRight(targetNode.getRight());
            targetNode.setValue(minRight);
        } else { //情况二:如果删除的节点只有一个子节点
            if (targetNode.getLeft() != null) { //  如果targetNode有左子节点
                if (parentNode.getLeft().getValue() == value) { //  targetNode是parent的左子节点
                    parentNode.setLeft(targetNode.getLeft());
                } else {    //  targetNode是parent的右子节点
                    parentNode.setRight(targetNode.getLeft());
                }
            } else { // 如果targetNode有右子节点
                if (parentNode.getLeft().getValue() == value) { //  targetNode是parent的左子节点
                    parentNode.setLeft(targetNode.getRight());
                } else {    //  targetNode是parent的右子节点
                    parentNode.setRight(targetNode.getRight());
                }

            }


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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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

    /**
     * 查找指定节点
     *
     * @param value：查找的值
     * @return
     */
    public Node search(int value) {
        //如果查找的值是当前节点的值
        if (this.value == value) {
            return this;
        }
        //如果查找的值小于当前节点的值，去左子树遍历
        if (value < this.value && this.left != null) {
            return this.left.search(value);
        }
        //如果查找的值大于当前节点的值，去右子树遍历
        if (value >= this.value && this.right != null) {
            return this.right.search(value);
        }
        return null;
    }

    /**
     * 查找指定节点的父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        //如果当前节点的左子节点值是查找的值或者当前节点右子节点值是查找的值，则该节点就是查找节点的父节点
        boolean b = (this.left != null && this.left.value == value) || (this.right != null && this.right.value == value);
        if (b) {
            return this;
        }
        //如果查找的值小于当前节点的值，去左子树遍历
        if (value < this.value && this.left != null) {
            return this.left.searchParent(value);
        }
        //如果查找的值大于当前节点的值，去右子树遍历
        if (value >= this.value && this.right != null) {
            return this.right.searchParent(value);
        }
        return null;
    }
}
