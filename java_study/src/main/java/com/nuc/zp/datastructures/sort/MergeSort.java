package com.nuc.zp.datastructures.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5, 6, 1, 2, 8, 7, 4, 3};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }


    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int t = 0;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[t++] = arr[i++];
        }

        while (j <= right) {
            temp[t++] = arr[j++];
        }

        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }

    }
}
