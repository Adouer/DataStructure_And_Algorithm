package com.adouer.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 复杂度：O(n^2)
 * @author adouer
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};
        int temp = 0;
        /*
        两个for循环，所以时间复杂度：O(n^2)
         */
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i + 1] < arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
        System.out.println("arr = " + Arrays.toString(arr));
    }
}
