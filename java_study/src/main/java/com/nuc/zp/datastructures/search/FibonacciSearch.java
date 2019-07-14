package com.nuc.zp.datastructures.search;

import java.util.Arrays;

public class FibonacciSearch {

    private static final int MAX_SIZE = 20;

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibonacciSearch(arr, 123));
    }


    private static int[] fib() {
        int[] f = new int[MAX_SIZE];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     *   mid = low + F(K-1)-1
     */
    private static int fibonacciSearch(int arr[], int value) {

        int low = 0;
        int high = arr.length - 1;
        int k = 0;
        int mid = 0;
        int f[] = fib();

        //获取斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(arr, f[k]);

        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {

            mid = low + f[k - 1] - 1;

            if (value < temp[mid]) {
                high = mid - 1;

                k--;
            } else if (value > temp[mid]) {
                low = mid + 1;
                k-=2;
            } else {
                if (mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }

}
