package com.adouer.dijkstra.prim;

import java.util.Arrays;

/**
 * 普里姆算法prim-修路问题
 * @author adouer
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建 ok
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000 这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};


        //创建 MGraph 对象
        MGraph graph = new MGraph(verxs);
        graph.createGraph(data, weight);
        graph.showGraph();
        //创建一个 MinTree 对象
        MinTree minTree = new MinTree();
        minTree.prim(graph,0);
    }

    /**
     * 最小生成树
     */
    static class MinTree {
        //编写 prim 算法，得到最小生成树

        /**
         * @param graph 图
         * @param v     表示从图的第几个顶点开始生成'A'->0 'B'->1...
         */
        public void prim(MGraph graph, int v) {
            /*
            1.从指定节（A）点开始遍历，找到当前节点的相邻节点中权值最小的边,进而找到下一个节点(G)
             */
            //visited[] 标记结点(顶点)是否被访问过
            int visited[] = new int[graph.verxs];
            for (int i = 0; i < visited.length; i++) {
                visited[i] = 0;
            }
            //将当前节点标记为已经访问
            visited[v] = 1;
            //h1 和 h2 记录两个顶点的下标
            int h1 = -1;
            int h2 = -1;
            //将 minWeight 初始成一个大数，后面在遍历过程中，会被替换
            int minWeight = 10000;
            for (int k = 1; k < graph.verxs; k++) {
                //因为有 graph.verxs 顶点，普利姆算法结束后，有 graph.verxs-1 边
                //这个是确定每一次生成的子图 ，和哪个结点的距离最近
                for (int i = 0; i < graph.verxs; i++) {// i 结点表示被访问过的结点
                    for (int j = 0; j < graph.verxs; j++) {//j 结点表示还没有访问过的结点
                        if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                            //替换 minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
                            minWeight = graph.weight[i][j];
                            h1 = i;
                            h2 = j;
                        }
                    }
                }

                // 找 到 一 条 边 是 最 小
                System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
                //将当前这个结点标记为已经访问
                visited[h2] = 1;
                //minWeight 重新设置为最大值 10000
                minWeight = 10000;
            /*
            2.所有找到的节点（A、G）的相邻节点中找到权值最小的边,进而找到下一个节点(B)
             */
            /*
            3.(A、G、B)中找下一个...
             */
                //直到遍历完所有的节点
            }

        }
    }

    /**
     * 类--图
     */
    static class MGraph {
        //节点个数
        int verxs;
        //节点数据
        char[] data;
        //权值
        int[][] weight;

        public MGraph(int verxs) {
            this.verxs = verxs;
            this.data = new char[verxs];
            this.weight = new int[verxs][verxs];
        }
        //创建图的邻接矩阵

        /**
         * @param data   图的各个顶点的值
         * @param weight 图的邻接矩阵
         */
        public void createGraph(char data[], int[][] weight) {
            this.data = data;
            this.weight = weight;
            /*int i, j;
            for (i = 0; i < this.verxs; i++) {//顶点
                this.data[i] = data[i];
                for (j = 0; j < verxs; j++) {
                    this.weight[i][j] = weight[i][j];

                }
            }*/
        }

        /**
         * 展示邻接矩阵
         */
        public void showGraph() {
            System.out.println("数据：" + Arrays.toString(this.data));
            System.out.println("邻接矩阵：");
            for (int[] ints : this.weight) {
                System.out.println(Arrays.toString(ints));
            }
        }
    }
}