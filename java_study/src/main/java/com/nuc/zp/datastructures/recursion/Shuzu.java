package com.nuc.zp.datastructures.recursion;

import java.util.Scanner;

/**
 * 问题 1575: [蓝桥杯][算法提高VIP]递归倒置字符数组
 * 时间限制: 1Sec 内存限制: 128MB 提交: 375 解决: 132
 * <p>
 * 题目描述
 * 完成一个递归程序，倒置字符数组。并打印实现过程
 * 递归逻辑为：
 * 当字符长度等于1时，直接返回
 * 否则，调换首尾两个字符，在递归地倒置字符数组的剩下部分
 * 输入
 * 字符数组长度及该数组
 * 输出
 * 在求解过程中，打印字符数组的变化情况。
 * 最后空一行，在程序结尾处打印倒置后该数组的各个元素。
 * 样例输入
 * 5 abcde
 * 样例输出
 * ebcda
 * edcba
 * <p>
 * edcba
 */
public class Shuzu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String str = scanner.next();
            show(0, str.toCharArray()
                    .length - 1, str.toCharArray()
            );
        }
    }

    private static void show(int start, int end, char[] str) {

        if (str.length == 1) {
            System.out.println("\n" + new String(str));
            return;
        }

        if (start > str.length || end < 0 || start == end || start > end) {
            System.out.println("\n" + new String(str));
            return;
        }
        char temp = str[start];
        str[start] = str[end];
        str[end] = temp;
        System.out.println(new String(str));
        show(start + 1, end - 1, str);

    }
}
