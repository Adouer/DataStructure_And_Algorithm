package com.adouer.search;

import java.util.ArrayList;

/**
 * 二分查找
 * 前提：数组有序
 *
 * @author adouer
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 9, 14, 19};
        int i = binarySearch(arr, 0, arr.length - 1, 191);
        System.out.println(i);
        ArrayList<Integer> integers = new ArrayList<>();

    }

    /**
     * 二分查找方法
     *
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        //目标在左边，向左递归
        if (target < arr[mid]) {
            return binarySearch(arr, left, mid - 1, target);
        } else if (target > arr[mid]) {
            return binarySearch(arr, mid + 1, right, target);
        } else {
            return mid;
        }
    }
}
