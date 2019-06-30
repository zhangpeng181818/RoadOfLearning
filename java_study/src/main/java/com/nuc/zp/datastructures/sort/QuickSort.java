package com.nuc.zp.datastructures.sort;

/**
 * 快速排序：
 * 是对冒泡排序的改进。
 * 基本思想：
 * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另一部分
 * 的所有数据都要小，然后再按此方法对两部分数据分别进行快速排序，整个排序过程可以
 * 递归进行，以此达到整个数据变成有序序列。
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2};
//        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort2(arr, 0, arr.length - 1);
        show(arr);
    }

    private static void sort(int[] arr) {
        quickSort2(arr, 0, arr.length - 1);
    }


    private static void quickSort2(int[] arr, int left, int right) {
        int i = left;
        int j = right;
        int key = arr[left];
        while (i < j) {
            while (j > i && arr[j] > key) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            while (i < j && arr[i] < key) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        if (i == j) {
            arr[i] = key;
        }
        if (left < i - 1) {
            quickSort2(arr, left, i - 1);
        }
        if (i + 1 < right) {
            quickSort2(arr, i + 1, right);
        }

    }

    private static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void show(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
