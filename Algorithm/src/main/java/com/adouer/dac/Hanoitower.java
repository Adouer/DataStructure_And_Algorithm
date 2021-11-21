package com.adouer.dac;

/**
 * 汉诺塔
 *
 * @author adouer
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoitower(5, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔移动方法
     *
     * @param num 需要移动的盘的个数
     * @param a   a铜柱
     * @param b   b铜柱
     * @param c   c铜柱
     */
    public static void hanoitower(int num, char a, char b, char c) {
        //思路：分两种情况
        //(1)只有一个盘
        //(2) num >=2,将最下面的看做一个，上面的所有看作一个
        if (num == 1) {
            System.out.println(a + "->" + c);
        } else {
            //将num-1个盘，从a->b
            hanoitower(num - 1, a, c, b);
            //最后一个a->c
            System.out.println(a + "->" + c);
            //将num-1个盘，从b->c
            hanoitower(num - 1, b, a, c);
        }


    }
}
