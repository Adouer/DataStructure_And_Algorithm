package com.adouer.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 两个for循环所以，时间复杂度O(n^2)
 *
 * @author adouer
 */
public class SelectSort {

    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        long start = System.currentTimeMillis();
        int[] ints = selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("ints = " + Arrays.toString(ints));
        System.out.println("耗时" + (end - start));

    }

    public static int[] selectSort(int[] arr) {
        // 减1，因为数组最后一个并不需要选，从最后两个中选出小的就已经结束了
        for (int i = 0; i < arr.length - 1; i++) {
            /*选出最小的*/
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            /*
            把最小的放在当前循环的首位
             */
            if (arr[minIndex] != arr[i]) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }
}
