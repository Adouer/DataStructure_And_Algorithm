package com.adouer.search;

/**
 * 插值查找
 *
 * @author adouer
 */
public class InsertSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
        int i = insertSearch(arr, 0, arr.length - 1, 99);
        System.out.println(i);
    }

    /**
     * 插值查找
     *
     * @param arr    被检索的数组
     * @param left   数组左下标
     * @param right  数组右下标
     * @param target 目标
     * @return
     */
    public static int insertSearch(int[] arr, int left, int right, int target) {
        System.out.println("```");
        if (left > right || target < arr[left] || target > arr[right]) {
            return -1;
        }
        int mid = left + (right - left) * (target - arr[left]) / (arr[right] - arr[left]);
        if (target < arr[mid]) {
            return insertSearch(arr, left, mid - 1, target);
        } else if (target > arr[mid]) {
            return insertSearch(arr, mid + 1, right, target);
        } else {
            return mid;
        }
    }
}
