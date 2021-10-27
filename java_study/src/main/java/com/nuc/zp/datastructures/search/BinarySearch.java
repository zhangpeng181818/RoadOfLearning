package com.nuc.zp.datastructures.search;

/**
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 22));
        System.out.println(binarySearch(arr, 8));
    }

    public static int binarySearch(int arr[], int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        int med = (left + right) / 2;
        int medVal = arr[med];
        if (value > medVal) {
            return binarySearch(arr, med + 1, right, value);
        } else if (value < medVal) {
            return binarySearch(arr, left, med - 1, value);
        } else {
            return med;
        }
    }

    public static int binarySearch(int arr[], int value) {
        int left = 0;
        int right = arr.length - 1;
        int med;
        while (left <= right) {
            med = (left + right) / 2;
            if (arr[med] > value) {
                right = med - 1;
            } else if (arr[med] < value) {
                left = med + 1;
            } else {
                return med;
            }
        }
        return -1;
    }
}
