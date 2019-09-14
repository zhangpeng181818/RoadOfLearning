package com.nuc.zp.datastructures.recursion;

import java.util.Scanner;

/**
 * 问题 1907: [蓝桥杯][算法提高VIP]递推求值
 * 时间限制: 1Sec 内存限制: 128MB 提交: 29 解决: 0
 * <p>
 * 题目描述
 * 已知递推公式：
 * <p>
 * F(n, 1)=F(n-1, 2) + 2F(n-3, 1) + 5,
 * <p>
 * F(n, 2)=F(n-1, 1) + 3F(n-3, 1) + 2F(n-3, 2) + 3.
 * <p>
 * 初始值为：
 * F(1, 1)=2,
 * F(1, 2)=3,
 * F(2, 1)=1,
 * F(2, 2)=4,
 * F(3, 1)=6,
 * F(3, 2)=5。
 * <p>
 * F(4,1) = F(3,2) + 2F(1,1) + 5 = 5+2*2+5 = 14
 * F(4,2) = F(3,1) + 3F(1,1) + 2F(1,2)+3=6+3*2+2*3+3=21
 * 输入n，输出F(n, 1)和F(n, 2)，由于答案可能很大，你只需要输出答案除以99999999的余数。
 * 输入
 * 输入第一行包含一个整数n。
 * 输出
 * 输出两行，第一行为F(n, 1)除以99999999的余数，第二行为F(n, 2)除以99999999的余数。
 * 样例输入
 * 4
 * 样例输出
 * 14
 * <p>
 * 21
 * 提示
 * 1<=n<=10^18。
 */
public class DiTuiQiuZhi {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            long n = scanner.nextLong();
            int j = (int) (n % 999999999);

            long[][] arr = new long[2][j];
            jisuan(j, arr);
            System.out.println(arr[0][j-1]);
            System.out.println();
            System.out.println(arr[1][j-1]);


        }
    }

    /**
     * F(1, 1)=2,
     * * F(1, 2)=3,
     * * F(2, 1)=1,
     * * F(2, 2)=4,
     * * F(3, 1)=6,
     * * F(3, 2)=5。
     * * F(n, 1)=F(n-1, 2) + 2F(n-3, 1) + 5,
     * * F(n, 2)=F(n-1, 1) + 3F(n-3, 1) + 2F(n-3, 2) + 3.
     */
    //972458713259872
    private static void jisuan(long n, long[][] arr) {
        arr[0][0] = 2;
        arr[1][0] = 3;
        arr[0][1] = 1;
        arr[1][1] = 4;
        arr[0][2] = 6;
        arr[1][2] = 5;
        for (int i = 4; i <= n; i++) {
            arr[0][i-1] = (arr[1][i - 2] + 2 * arr[0][i - 4] + 5)% 999999999;
            arr[1][i-1] = (arr[0][i - 2] + 3 * arr[0][i - 4] + 2 * arr[1][i - 4] + 3)% 999999999;
        }
    }
}
