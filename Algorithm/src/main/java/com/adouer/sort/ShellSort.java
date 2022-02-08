package com.adouer.sort;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author adouer
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0,-8,-1};
        int[] ints = shellSortChange(arr);
        System.out.println("shellSortChange"+Arrays.toString(ints));
        int[] ints1 = shellSortMove(arr);
        System.out.println("shellSortMove"+Arrays.toString(ints1));
    }

    /**
     * 希尔排序(交换)
     * 这里的实现方法是按照自己的理解实现的，比教程里好理解一些
     *
     * @return
     */
    public static int[] shellSortChange(int[] arr) {
        int temp = 0;
        /*
            1.最外层for,控制分组次数，第一次分成10/2=5组，第二次分成5/2=2组，第三次分成2/2=1组
            2.第二三层for循环其实构成了完整的冒泡排序
                2.1第二层for循环，控制循环次数
                2.2第三层for循环，把各组中最大的放在末尾（冒泡）
         */
        for (int j = arr.length / 2; j > 0; j /= 2) {
            for (int k = 0; k < arr.length - 1; k += j) {
                for (int i = 0; i < arr.length - 1; i++) {
                    if (i + j < arr.length && arr[i + j] < arr[i]) {
                        temp = arr[i + j];
                        arr[i + j] = arr[i];
                        arr[i] = temp;
                    }
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    /**
     * 希尔排序（移动）
     *
     * @param arr
     * @return
     */
    public static int[] shellSortMove(int[] arr) {
        int indexValue = 0;
        int index = 0;
        /*
            1.最外层for循环，控制分组
            2.内层for循环+while是直接插入排序，增加步长版本
         */

        for (int j = arr.length / 2; j > 0; j /= 2) {
            for (int i = j; i < arr.length; i++) {
                indexValue = arr[i];
                index = i - j;
                while (index >= 0 && indexValue < arr[index]) {
                    arr[index + j] = arr[index];
                    index -= j;
                }
                arr[index + j] = indexValue;
            }
        }
        return arr;
    }
}
