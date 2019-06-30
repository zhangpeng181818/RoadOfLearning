package com.nuc.zp.datastructures.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 希尔排序 ：
 * 也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8,9,1,7,2,3,5,4,6,0};

        int[] arr = new int[80000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        System.out.println(LocalDateTime.now());


        sort(arr);
//        show(arr);
        System.out.println(LocalDateTime.now());

    }

    private static void sort(int[] arr) {
        int gap = arr.length / 2;
        int n = 0;
        while (gap > 0) {
            for (int j = gap; j < arr.length; j++) {

                for (int i = j - gap; i >= 0; i -= gap) {
                    if (arr[i] > arr[i + gap]) {
                        int temp = arr[i];
                        arr[i] = arr[i + gap];
                        arr[i + gap] = temp;
                    }
                }
            }
            gap /= 2;
//            System.out.println("希尔排序第" + (++n) + "轮=" + Arrays.toString(arr));
        }
    }

    private static void sort2(int[] arr) {
        int gap = arr.length / 2;
        int n = 0;
        boolean flag = false;
        while (gap > 0) {
            for (int j = gap; j < arr.length; j++) {

                int temp = arr[j];
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                    flag = true;
                }
                if (flag) {
                    arr[j] = temp;
                    flag = false;
                }
            }
            gap /= 2;
//            System.out.println("希尔排序第" + (++n) + "轮=" + Arrays.toString(arr));
        }
    }


    private static void shellSort(int[] arr) {
        int n = 0;
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++n) + "轮=" + Arrays.toString(arr));

        }
    }

    private static void show(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
