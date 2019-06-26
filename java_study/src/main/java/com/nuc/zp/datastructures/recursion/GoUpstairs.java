package com.nuc.zp.datastructures.recursion;

/**
 * 题目描述
 * 有一楼梯共m级，刚开始时你在第一级，若每次只能跨上一级或者二级，要走上m级，共有多少走法？注：规定从一级到一级有0种走法。
 * 给定一个正整数int n，请返回一个数，代表上楼的方式数。保证n小于等于100。为了防止溢出，请返回结果Mod 1000000007的值。
 * 测试样例：
 * 3
 * 返回：2
 */
public class GoUpstairs {
    static int total = 0;

    public static void main(String[] args) {

    }

    public static int countWays(int n) {
        // write code here
        return 0;
    }


    public static int countWays3(int n) {
        return fib(n);
    }
    private static int fib(int n) {
        int a = 1;
        int b = 1;
        for(int i = 3; i <= n; i++){
            int temp = b;
            b = (a + b) % 1000000007;
            a  = temp;
        }
        return b;
    }


}
