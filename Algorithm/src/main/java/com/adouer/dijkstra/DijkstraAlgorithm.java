package com.adouer.dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        /*
        构建图
         */
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.djs(6);
        graph.showDijkstra();
    }

    /**
     * 构建图
     */
    public static class Graph {
        /**
         * 顶点
         */
        private char[] vertex;
        /**
         * 邻接矩阵
         */
        private int[][] matrix;
        /**
         * 组合核心类
         */
        private VisitedVertex vv;

        public Graph(char[] vertex, int[][] matrix) {
            this.vertex = vertex;
            this.matrix = matrix;
        }

        /**
         * 显示结果
         */
        public void showDijkstra() {
            vv.show();
        }

        /**
         * 展示邻接矩阵
         */
        public void showGraph() {
            for (int[] ints : matrix) {
                System.out.println(Arrays.toString(ints));
            }
        }

        /**
         * 迪杰斯特算法
         *
         * @param index
         */
        public void djs(int index) {
            vv = new VisitedVertex(this.vertex.length, index);
            update(index);
            System.out.println("访问G后：already" + Arrays.toString(vv.already_arr));
            System.out.println("访问G后：pre_visited" + Arrays.toString(vv.pre_visited));
            System.out.println("访问G后：dis" + Arrays.toString(vv.dis));
            for (int i = 1; i < this.vertex.length; i++) {
                index = vv.updateArr();
                update(index);
                System.out.println("访问" + i + "后：already" + Arrays.toString(vv.already_arr));
                System.out.println("访问" + i + "后：pre_visited" + Arrays.toString(vv.pre_visited));
                System.out.println("访问" + i + "后：dis" + Arrays.toString(vv.dis));
            }
        }

        /**
         * 为三个数组赋值
         */
        public void update(int index) {
            int len = 0;
            //指定节点（G点）到当前节点（index节点）的距离+当前节点到周围各个节点的距离
            for (int i = 0; i < matrix[index].length; i++) {
                len = vv.dis[index] + matrix[index][i];
                //如果i节点没有被访问过，并且len<i节点直接到指定节点（G节点）的距离
                if (vv.already_arr[i] == 0 && len < vv.dis[i]) {
                    //更新前驱节点
                    vv.pre_visited[i] = index;
                    //更新最短距离
                    vv.dis[i] = len;
                }
            }

        }
    }

    /**
     * 构建迪杰斯特拉算法核心
     */
    static class VisitedVertex {
        /**
         * 记录各个顶点是否访问过 1 表示访问过,0 未访问,会动态更新
         */
        public int[] already_arr;
        /**
         * 每个下标对应的值为前一个顶点下标, 会动态更新
         */
        public int[] pre_visited;
        /**
         * 记录出发顶点到其他所有顶点的距离,比如 G 为
         * 出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求的最短距离就会存放到 dis
         */
        public int[] dis;

        /**
         * 构造方法赋值
         *
         * @param length 节点总数
         * @param index  出发顶点下标（G）
         */
        public VisitedVertex(int length, int index) {
            this.already_arr = new int[length];
            this.pre_visited = new int[length];
            this.dis = new int[length];
            Arrays.fill(dis, 65535);
            //设置出发顶点的访问距离为 0
            this.dis[index] = 0;
            //设置出发顶点被访问过
            this.already_arr[index] = 1;
        }

        /**
         * 继续选择并返回新的访问节点，如 G 完成后，A作为新节点返回
         *
         * @return
         */
        public int updateArr() {
            int min = 65535, index = 0;
            //选出了离上个节点最近且没有访问过的节点
            for (int i = 0; i < already_arr.length; i++) {
                if (already_arr[i] == 0 && dis[i] < min) {
                    min = dis[i];
                    index = i;
                }
            }
            already_arr[index] = 1;
            return index;
        }

        public void show() {
            System.out.println("==========================");
// 输 出 already_arr
            for (int i : already_arr) {
                System.out.print(i + " ");
            }
            System.out.println();
// 输 出 pre_visited
            for (int i : pre_visited) {
                System.out.print(i + " ");
            }
            System.out.println();
// 输 出 dis
            for (int i : dis) {
                System.out.print(i + " ");
            }
            System.out.println();
            //为了好看最后的最短距离，我们处理
            char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
            int count = 0;
            for (int i : dis) {
                if (i != 65535) {
                    System.out.print(vertex[count] + "(" + i + ") ");
                } else {
                    System.out.println("N ");
                }
                count++;
            }
            System.out.println();
        }
    }
}

