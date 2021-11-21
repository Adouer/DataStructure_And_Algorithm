package com.adouer.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图
 */
public class GraphDemo {
    public static void main(String[] args) {
        //传入n,构建一个n*n的邻接矩阵
        GraphDemo graph = new GraphDemo(5);
        //为所有节点赋值
        graph.vertexList = Arrays.asList(new String[]{"A", "B", "C", "D"});
        //节点关系赋值（实例化邻接矩阵）
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        //展示邻接矩阵
        graph.show();
        //输出边的数量
        System.out.println(graph.numOfEdges);
        //dfs深度遍历
        graph.dfs();

    }

    /**
     * 存放顶点的集合
     */
    private List<String> vertexList;
    /**
     * 存放邻接矩阵
     */
    private int[][] edges;
    /**
     * 存放边的数量
     */
    private int numOfEdges;
    /**
     * 保存是否被访问
     */
    private boolean[] isVisited;

    /**
     * 构造方法
     *
     * @param n
     */
    public GraphDemo(int n) {
        this.edges = new int[n][n];
    }

    /**
     * 节点关系赋值（实例化邻接矩阵）
     *
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 展示邻接矩阵
     */
    public void show() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回v1和v2的权值
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 返回当前节点的下一个邻接节点，有返回下标，没有返回-1
     *
     * @return
     */
    public int getNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接结点的下标来获取下一个邻接结点
     *
     * @param v1 当前节点
     * @param v2 当前节点的邻接节点（该节点已经被访问过了）
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度遍历【递归】
     */
    public void dfs(int i) {
        //首先我们访问该结点,输出
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问
        isVisited[i] = true;
        //查找当前节点的下一个节点
        int w = getNeighbor(i);
        //如果当前节点有下一个邻接节点
        if (w != -1) {
            //如果没有被访问过
            if (!isVisited[w]) {
                dfs(w);
            }
            //如果w结点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 遍历所有节点
     * 【用于当前节点的邻接节点没有找到，即 getNeighbor(i)返回-1时，按顺序对下一个节点进行遍历】
     */
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
    }
}
