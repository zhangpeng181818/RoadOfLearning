package com.nuc.zp.datastructures.sort;

import java.time.LocalDateTime;

/**
 * 插入排序
 * auther: ZP
 * time:   2019/6/29 18:26
 */
public class InsertSort {

    public static void main(String[] args) {
//        int[] arr = {1,10,2,1,5,0,-1,1,-3};
        int[] arr = new int[7];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000000);
        }

        System.out.println(LocalDateTime.now());
        sort(arr);
        show(arr);
        System.out.println(LocalDateTime.now());

    }

    private static void sort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            boolean flag = false;
            int j = i;
            while (j - 1 >= 0 && arr[i] < arr[j - 1]) {
                flag = true;
                j--;
            }
            if (flag){
                int temp = arr[i];
                System.arraycopy(arr, j, arr, j + 1, i - j);
                arr[j] = temp;
            }

            }
    }

    private static void show(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
