package com.nuc.zp.datastructures.recursion;

import java.util.Scanner;

/**
 * 问题 1544: [蓝桥杯]特殊的质数肋骨
 *
 * <p>
 * 题目描述
 * 农民约翰母牛总是产生最好的肋骨。你能通过农民约翰和美国农业部标记在每根肋骨上的数字认出它们。
 * 农民约翰确定他卖给买方的是真正的质数肋骨，是因为从右边开始切下肋骨，每次还剩下的肋骨上的数字都组成一个质数。
 * <p>
 * 例如有四根肋骨的数字分别是：7  3  3  1，那么全部肋骨上的数字  7331是质数；三根肋骨  733是质数；
 * 二根肋骨  73  是质数；当然,最后一根肋骨  7  也是质数。7331  被叫做长度  4  的特殊质数。
 * <p>
 * 写一个程序对给定的肋骨的数目  N  (1< =N< =8),求出所有的特殊质数。数字1不被看作一个质数。
 * 输入
 * 单独的一行包含N。
 * 输出
 * 按顺序输出长度为  N  的特殊质数,每行一个。
 * 样例输入
 * 4
 * 样例输出
 * 2333
 * 2339
 * 2393
 * 2399
 * 2939
 * 3119
 * 3137
 * 3733
 * 3739
 * 3793
 * 3797
 * 5939
 * 7193
 * 7331
 * 7333
 * 7393
 */
public class Lanqiao1544 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            zhishu(n);

        }

    }

    private static void zhishu(int n) {
        if (n <= 0 || n > 8) {
            return;
        }
        int min = 0, max = 0, weight = 0;
        switch (n) {
            case 1:
                min = 2;
                max = 7;
                weight = 1;
                break;
            case 2:
                min = 11;
                max = 97;
                weight = 10;
                break;
            case 3:
                min = 101;
                max = 997;
                weight = 100;
                break;
            case 4:
                min = 1001;
                max = 9997;
                weight = 1000;
                break;
            case 5:
                min = 10001;
                max = 99997;
                weight = 10000;
                break;
            case 6:
                min = 100001;
                max = 900007;
                weight = 100000;
                break;
            case 7:
                min = 1000001;
                max = 9000007;
                weight = 1000000;
                break;
            case 8:
                min = 10000001;
                max = 90000007;
                weight = 10000000;
                break;
        }
        print(min, max, weight);
    }

    private static void print(int min, int max, int weight) {
        for (int i = min; i <= max; i++) {

            panduan(i, i, weight);
        }
    }

    private static void panduan(int n, int N, int weight) {
        while (weight > 0 && isPrime(N / weight)) {
            weight = weight / 10;
            if (weight == 0) {
                System.out.print(N + " ");
                break;
            }
        }

    }

    private static boolean isPrime(int n) {
        if (n <= 3) {
            return n > 1;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}


