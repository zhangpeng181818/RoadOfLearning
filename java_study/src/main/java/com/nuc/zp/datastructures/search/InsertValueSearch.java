package com.nuc.zp.datastructures.search;

/**
 * 差值查找
 *
 * Tip:
 *      1) 对于数据量较大，关键字分布比较均匀的查找表来说，采用差值查找，速度较快。
 *      2）关键字分布不均匀的情况下，该方法不一定比折半查找要好
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 8));
    }

    private static int insertValueSearch(int arr[], int left, int right, int value) {
        if (left > right || value < arr[0] || value > arr[arr.length-1]) {
            return -1;
        }
        int med = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);

        int medVal = arr[med];
        if (value > medVal) {
            return insertValueSearch(arr, med + 1, right, value);
        } else if (value < medVal) {
            return insertValueSearch(arr, left, med - 1, value);
        } else {
            return med;
        }
    }
}
