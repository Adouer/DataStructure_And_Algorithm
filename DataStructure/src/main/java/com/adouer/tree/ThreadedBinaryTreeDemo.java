package com.adouer.tree;

import lombok.Data;

/**
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试: 以 10 号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10 号结点的前驱结点是 =" + leftNode);    // 3
        System.out.println("10 号结点的后继结点是=" + rightNode);    // 1


        //当线索化二叉树后，能在使用原来的遍历方法
        //threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6

    }

    /**
     * 节点类
     */
    @Data
    static class HeroNode {
        private int no;
        private String name;
        private HeroNode left; //左子节点 默认 null
        private HeroNode right; //右子节点 默认 null
        //1. 如果 leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
        //2. 如果 rightType == 0 表示指向是右子树, 如果 1 表示指向后继结点
        private int leftType;
        private int rightType;

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }

        //编写前序遍历的方法
        public void preOrder() {
            System.out.println(this); //先输出父结点
            //递归向左子树前序遍历
            if (this.left != null) {
                this.left.preOrder();
            }
            //递归向右子树前序遍历
            if (this.right != null) {
                this.right.preOrder();
            }
        }

        //中序遍历
        public void infixOrder() {
            //递归向左子树中序遍历
            if (this.left != null) {
                this.left.infixOrder();
            }
            //输出父结点
            System.out.println(this);
            //递归向右子树中序遍历
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        //后序遍历
        public void postOrder() {
            if (this.left != null) {
                this.left.postOrder();
            }
            if (this.right != null) {
                this.right.postOrder();
            }
            //输出父结点
            System.out.println(this);
        }

        /**
         * 前序遍历查找指定节点
         *
         * @param no
         * @return
         */
        public HeroNode preOrderSearch(int no) {
            //判断当前节点是不是所查找的节点
            if (this.no == no) {
                return this;
            }
            //保存每次返回的结果，用于持续遍历
            HeroNode resNode = null;
            //判断左子树
            if (this.left != null) {
                resNode = this.left.preOrderSearch(no);
            }
            //判断右子树
            if (this.right != null) {
                resNode = this.right.preOrderSearch(no);
            }
            return resNode;
        }

        /**
         * 中序遍历查找指定节点
         *
         * @param no
         * @return
         */
        public HeroNode infixOrderSearch(int no) {

            HeroNode resNode = null;
            //判断左子树
            if (this.left != null) {
                resNode = this.left.infixOrderSearch(no);
            }
            //判断当前节点是不是所查找的节点
            if (this.no == no) {
                return this;
            }
            //判断右子树
            if (this.right != null) {
                resNode = this.right.infixOrderSearch(no);
            }
            return resNode;
        }

        /**
         * 后序遍历查找指定节点
         *
         * @param no
         * @return
         */
        public HeroNode postOrderSearch(int no) {

            HeroNode resNode = null;
            //判断左子树
            if (this.left != null) {
                resNode = this.left.postOrderSearch(no);
            }
            //判断右子树
            if (this.right != null) {
                resNode = this.right.postOrderSearch(no);
            }
            //判断当前节点是不是所查找的节点
            if (this.no == no) {
                return this;
            }
            return resNode;
        }

        /**
         * 根据no删除子节点
         * 1.如果删除的节点是叶子节点，则删除该节点
         * 2.如果删除的节点是非叶子节点，则删除该子树
         *
         * @param no
         */
        public void delNode(int no) {
            //2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将 this.left = null; 并且就返回(结束递归删除)
            if (this.left != null && this.left.no == no) {
                this.left = null;
                return;
            }
            //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将 this.right= null ;并且就返回(结束递归删除)
            if (this.right != null && this.right.no == no) {
                this.right = null;
                return;
            }
            //4.我们就需要向左子树进行递归删除
            if (this.left != null) {
                this.left.delNode(no);
            }
            //5.则应当向右子树进行递归删除
            if (this.right != null) {
                this.right.delNode(no);
            }
        }
    }
}

//定义 ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private ThreadedBinaryTreeDemo.HeroNode root;
    //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre 总是保留前一个结点
    private ThreadedBinaryTreeDemo.HeroNode pre = null;

    public void setRoot(ThreadedBinaryTreeDemo.HeroNode root) {
        this.root = root;
    }

    //重载 threadedNodes 方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList() {

        //定义一个变量，存储当前遍历的结点，从 root 开始
        ThreadedBinaryTreeDemo.HeroNode node = root;
        while (node != null) {
            //循环的找到 leftType == 1 的结点，第一个找到就是 8 结点
            //后面随着遍历而变化,因为当 leftType==1 时，说明该结点是按照线索化
            //处理后的有效结点
            while (node.getLeftType() == 0) {
                node = node.getLeft();

            }

            //打印当前这个结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点,就一直输出
            while (node.getRightType() == 1) {
                //获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = node.getRight();
        }
    }

//编写对二叉树进行中序线索化的方法

    /**
     * @param node 就是当前需要线索化的结点
     */
    public void threadedNodes(ThreadedBinaryTreeDemo.HeroNode node) {

        //如果 node==null,  不能线索化
        if (node == null) {
            return;
        }
        //(一)先线索化左子树
        threadedNodes(node.getLeft());
        //(二)线索化当前结点[有难度]
        //处理当前结点的前驱结点
        //以 8 结点来理解
        //8 结点的.left = null , 8 结点的.leftType = 1
        if (node.getLeft() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;
        //(三)在线索化右子树
        threadedNodes(node.getRight());
    }
}