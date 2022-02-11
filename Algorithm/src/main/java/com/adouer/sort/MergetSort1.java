package com.adouer.sort;

import java.util.Arrays;

/**
 * 归并排序2
 *
 * @author adouer
 */
public class MergetSort1 {

    public static void main(String[] args) {
        int arr[] = {8, 7, 6, 5, 4, 3, 2, 1};
        //归并排序需要一个额外空间
        int temp[] = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println("归并排序后=" + Arrays.toString(arr));
    }

    /**
     * 分+合方法
     *
     * @param arr   需要排序的数组
     * @param left  中值左边的指针
     * @param right 中值右边的指针
     * @param temp  临时数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            //计算中值
            int mid = (left + right) / 2;
            //向左递归
            mergeSort(arr, left, mid, temp);
            //向右递归
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }

    }


    /**
     * 合并的方法
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        /*
            比较左右指针指向的值，从小到大放到放入临时数组，然后重新合并入原始数组
         */
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            //左指针小于右指针，左边的先放入临时数组
            //左指针大于右指针，右边的先放入临时数组
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                i++;
                t++;
            } else {
                temp[t] = arr[j];
                j++;
                t++;
            }
        }
        //当一边放完后，将另外一边都放入临时数组
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //将临时数组合并进入原数组
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}