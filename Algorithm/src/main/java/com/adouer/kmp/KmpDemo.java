package com.adouer.kmp;

import java.util.Arrays;

/**
 * kmp算法
 */
public class KmpDemo {

    public static void main(String[] args) {
        String src = "BBC ABCDAB ABCDABCDABDE";
        String dest = "ABCDABD";
        int[] ints = kmpNext(dest);
        System.out.println("ints = " + Arrays.toString(ints));
        int i = kmpSearch(src, dest, ints);
        System.out.println("i = " + i);
    }

    /**
     * 求指定字符串的部分匹配表
     *
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest) {
        //保存部分匹配值表
        int[] next = new int[dest.length()];

        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     * 求首次匹配的下标，没有返回-1
     *
     * @param str1 源字符串
     * @param str2 子串
     * @param next 子串的部分匹配表
     * @return
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }
}
