package com.adouer.tree.avl;

/**
 * 平衡二叉树
 * 是在二叉排序树的基础上建立的
 */
public class AvlTreeDemo {
    public static void main(String[] args) {
        //左旋转
        int[] arr = {4, 3, 6, 5, 7, 8};
        //需要右旋转
        int[] arr2 = {10, 12, 8, 9, 7, 6};
        //需要双旋转
        int[] arr3 = {10, 11, 7, 6, 8, 9};

        //创建一个 AVLTree对象
        AvlTree avlTree = new AvlTree();
        //添加结点
        for (int i = 0; i < arr3.length; i++) {
            avlTree.add(new Node(arr3[i]));
        }
        //遍历
        System.out.println("中序遍历");
        avlTree.fixOrder();

        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); //    4
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); //     1
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); //    3
        System.out.println("当前的根结点=" + avlTree.getRoot());//    4


    }

}

/**
 * 平衡二叉树
 */
class AvlTree {
    /**
     * 根节点
     */
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

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
        //当添加完一个结点后，如果: (右子树的高度-左子树的高度) > 1 , 左旋转
        if(rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if(right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子结点进行右旋转
                right.rightRotate();
                //然后在对当前结点进行左旋转
                leftRotate(); //左旋转..
            } else {
                //直接进行左旋转即可
                leftRotate();
            }
            return ; //必须要!!!
        }

        //当添加完一个结点后，如果 (左子树的高度 - 右子树的高度) > 1, 右旋转
        if(leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前结点的左结点(左子树)->左旋转
                left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转即可
                rightRotate();
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

    /**
     * 返回以该节点为根节点的数的高度
     *
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 返回左子树的高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 返回右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 左旋转方法
     */
    public void leftRotate() {

        //创建新的结点，以当前根结点的值
        Node newNode = new Node(this.value);
        //把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把新的结点的右子树设置成带你过去结点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子树(左子结点)设置成新的结点
        left = newNode;


    }

    /**
     * 右旋转
     */
    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
}
