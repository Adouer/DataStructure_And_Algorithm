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
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //展示邻接矩阵
        graph.show();
        //输出边的数量
        System.out.println(graph.numOfEdges);
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
    public void show(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     *  返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回v1和v2的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }
}
