package com.adouer.dynamic;

/**
 * 背包问题
 *
 * @author adouer
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        //物品的重量
        int[] w = {0, 1, 4, 3};
        //物品的价值 这里val[i] 就是前面讲的v[i]
        int[] val = {0, 1500, 3000, 2000};
        //背包的容量
        int m = 4;
        //物品的个数
        int n = val.length;
        //创建二维数组
        int[][] v = new int[n][m + 1];
        int[][] path = new int[n][m + 1];
        /*
          0行，0列赋值
         */
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; v[0].length > i; i++) {
            v[0][i] = 0;
        }
        //行
        for (int i = 1; i < v.length; i++) {
            //列
            for (int j = 1; j < v[i].length; j++) {
                //给v[][]赋值，
                //如果当前物品重量>当前容量，把本列上一个物品给当前
                if (w[i] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //val[i] + v[i - 1][j - w[i]]是放了当前物品，v[i - 1][j]是没有放当前物品
                    // v[i][j] = Math.max(v[i - 1][j], val[i] + v[i - 1][j - w[i]]);

                    if (v[i - 1][j] > val[i] + v[i - 1][j - w[i]]) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        v[i][j] = val[i] + v[i - 1][j - w[i]];
                        //把当前的情况记录到
                        path[i][j] = 1;
                    }

                }
            }
        }
        System.out.println("容量价格分布表：");
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
                //System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("path:");
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1;    //列的最大下标
        while (i > 0 && j > 0) { //从 path 的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d 个商品放入到背包\n", i);
                j -= w[i];
            }
            i--;
        }
    }
}