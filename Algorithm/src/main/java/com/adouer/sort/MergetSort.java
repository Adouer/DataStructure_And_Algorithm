package com.adouer.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author adouer
 */
public class MergetSort {

    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        //归并排序需要一个额外空间
        int temp[] = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println("归并排序后=" + Arrays.toString(arr));
    }

    /**
     * 分+合方法
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            //中间索引
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
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

        //初始化i,左边有序序列的初始索引
        int i = left;
        //初始化 j, 右边有序序列的初始索引
        int j = mid + 1;
        //指向 temp 数组的当前索引
        int t = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }


//(二)
//把有剩余数据的一边的数据依次全部填充到 temp
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
//(三)将临时数组中的数据合并到真实数组arr中
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}