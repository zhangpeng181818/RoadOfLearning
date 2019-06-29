package com.nuc.zp.datastructures.sort;

import java.time.LocalDateTime;

/**
 * 选择排序
 * auther: ZP
 * time:   2019/6/29 16:38
 */
public class SelectionSort {
    public static void main(String[] args) {
//        int[] arr = {2,1,5,0,-1};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000000);
        }

        System.out.println(LocalDateTime.now());
        sort(arr);
//        show(arr);
        System.out.println(LocalDateTime.now());
    }

    private static void sort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static void show(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

}
