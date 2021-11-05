package com.adouer.tree;

import lombok.Data;

/**
 * 二叉树
 */

public class BinaryTreeDeme {
    public static void main(String[] args) {
        /*
         *遍历
         */
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode root = new HeroNode(1, " 宋 江 ");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        // 测 试
        System.out.println("前序遍历"); // 1,2,3,5,4
        binaryTree.preOrder();
        // 测 试
        System.out.println("中序遍历");
        binaryTree.infixOrder(); // 2,1,5,3,4
        System.out.println("后序遍历");
        binaryTree.postOrder(); // 2,5,4,3,1
        /*
        查找指定节点
         */
        int no = 3;
        //前序遍历查找指定节点
        System.out.println("前序遍历查找指定节点");
        HeroNode preNode = binaryTree.preOrderSearch(no);
        System.out.println(preNode);
        //中序遍历查找指定节点
        System.out.println("中序遍历查找指定节点");
        HeroNode infixOrderSearch = binaryTree.infixOrderSearch(no);
        System.out.println(infixOrderSearch);
        //后序遍历查找指定节点
        System.out.println("后序遍历查找指定节点");
        HeroNode postOrderSearch = binaryTree.postOrderSearch(no);
        System.out.println(postOrderSearch);

        /*
        删除
         */
        System.out.println("删除");
        System.out.println("删除前");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除后");
        binaryTree.preOrder();
    }

    /**
     * 节点类
     */
    @Data
    static
    class HeroNode {
        private int no;
        private String name;
        private HeroNode left; //左子节点 默认 null
        private HeroNode right; //右子节点 默认 null

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
        /**
         * 二叉树类
         */
        @Data
        static
        class BinaryTree {
            private HeroNode root;

            public void setRoot(HeroNode root) {
                this.root = root;
            }

            //前序遍历
            public void preOrder() {
                if (this.root != null) {
                    this.root.preOrder();
                } else {
                    System.out.println("二叉树为空，无法遍历");
                }
            }

            //中序遍历
            public void infixOrder() {
                if (this.root != null) {
                    this.root.infixOrder();
                } else {
                    System.out.println("二叉树为空，无法遍历");
                }
            }

            //后序遍历
            public void postOrder() {

                if (this.root != null) {
                    this.root.postOrder();
                } else {
                    System.out.println("二叉树为空，无法遍历");
                }
            }

            //前序遍历指定节点
            public HeroNode preOrderSearch(int no) {
                //根节点不为空
                if (this.root != null) {
                    return this.root.preOrderSearch(no);
                } else {
                    return null;
                }
            }

            //中序遍历指定节点
            public HeroNode infixOrderSearch(int no) {
                //根节点不为空
                if (this.root != null) {
                    return this.root.infixOrderSearch(no);
                } else {
                    return null;
                }
            }

            //后序遍历指定节点
            public HeroNode postOrderSearch(int no) {
                //根节点不为空
                if (this.root != null) {
                    return this.root.postOrderSearch(no);
                } else {
                    return null;
                }
            }

            //删除结点
            public void delNode(int no) {
                if (root != null) {
                    //判断root是不是就是要删除结点
                    if (root.getNo() == no) {
                        root = null;
                    } else {
                        //递归删除
                        root.delNode(no);
                    }
                } else {
                    System.out.println("空树，不能删除~");
                }
            }
        }
    }