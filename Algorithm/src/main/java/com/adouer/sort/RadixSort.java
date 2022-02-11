package com.adouer.sort;

import java.util.Arrays;

/**
 * 基数排序【桶排序】
 * 负数会越界，所以·····（垃圾），感受下思想得了
 * @author adouer
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214,5,1,66,2343};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 基数排序实现
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {
        /*
            计算最桶排次数【数组中的最大数的最高位数】
         */
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        int length = Integer.toString(max).length();
        for (int k = 0, n = 1; k < length; k++, n *= 10) {
            //二维数组记录桶排序中的数据
            int[][] bucket = new int[10][arr.length];
            //记录每个桶当前存放的数量
            int[] count = new int[10];
            int radixNum = 0;
            //将数组中的数按照个位放入桶中
            for (int i = 0; i < arr.length; i++) {
                radixNum = arr[i] / n % 10;
                bucket[radixNum][count[radixNum]++] = arr[i];
            }
            int index = 0;
            //将桶中的数据取出，重新放入数组中
            for (int i = 0; i < bucket.length; i++) {
                for (int j = 0; j < count[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
            }
        }
    }
}
