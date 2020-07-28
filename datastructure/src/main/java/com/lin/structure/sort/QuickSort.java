package com.lin.structure.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = { 1, 3, 4, 5, 2, 6, 9, 7, 8, 0 };
        quickSort(arr, 0, arr.length - 1);

        System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int baseIndex = division(arr, left, right);

            quickSort(arr, left, baseIndex - 1);
            quickSort(arr, baseIndex + 1, right);
        }
    }


    public static int division(int[] arr, int left, int right) {

        int base = arr[left];

        while (left < right) {

            while (left < right && arr[right] >= base) {
                right--;
            }
            arr[left] = arr[right];

            while (left < right && arr[left] <= base) {
                left++;
            }
            arr[right] = arr[left];
        }

        arr[left] = base;

        return left;
    }

}
