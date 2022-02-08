package com.adouer.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author adouer
 */
public class InsertSort {

    public static void main(String[] args) {

        int[] arr = {101, 34, 119, 1};
        int[] ints = insertSort(arr);
        System.out.println(Arrays.toString(ints));

    }

    /**
     * 插入排序
     * @param arr 需要被排序的数组
     * @return
     */
    public static int[] insertSort(int[] arr) {
         /*
        第一次
         */
        //把34保存到indexValue中，用34和101比较，如果34<101,34的位置（arr[1]）换成101,101的位置（arr[0]）换成34，
        //{101, 34, 119, 1}->{101, 101, 119, 1}->{34, 101, 119, 1};
        /*
        第二次
         */
        //index后移，判断119和101的关系，发现119<101不成立，
        /*
        第三次
         */
        // index = 2,判断1和119的关系，1<119:{34, 101, 119, 1}->{34, 101, 119, 119}
        // index = 1,判断1和101的关系：1<101:{34, 101, 119, 119}->{34, 101, 101, 119}
        // index = 0,判断1和34的关系，1<34:{34, 101, 101, 119}->{34, 34, 101, 119}
        // index = -1,不进while循环，将1赋值给arr[index +1] ->[1, 34, 101, 119]
        System.out.println("初始数组" + Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            int indexValue = arr[i];
            int index = i - 1;
            while (index >= 0 && indexValue < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
                System.out.print(Arrays.toString(arr));
            }

            arr[index + 1] = indexValue;
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

}
