package com.adouer.search;

/**
 * 顺序查找
 *
 * @author adouer
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,-2,3,66,97};
        int i = seqSearch(arr, 66);
        System.out.println(i);
    }

    public static int seqSearch(int[] arr,int target) {
        for (int i = 0; i < arr.length; i++) {
            if (target == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
