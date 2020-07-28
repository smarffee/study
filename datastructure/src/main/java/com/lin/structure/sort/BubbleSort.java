package com.lin.structure.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * O(n^2)
 */
public class BubbleSort {

    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, -2};
        int[] arr = {3, 9, -1, 10, 20};
//        int[] arr = {1, 2, 3, 4, 5};

        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean changed = false; //如果没有发生交换，说明排序已经排好，提前结束
            //每一轮排序执行的算法
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    changed = true;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序之后: " + Arrays.toString(arr));

            if (!changed) {
                break;
            }
        }

/*
        int temp;
        //第一趟排序, 第一大的放到倒数第一的位置
        for (int j = 0; j < arr.length - 1 - 0; j++) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第一趟排序之后: " + Arrays.toString(arr));

        //第二趟排序, 第二大的放到倒数第二的位置
        for (int j = 0; j < arr.length - 1 - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第二趟排序之后: " + Arrays.toString(arr));

        //第三趟排序, 第三大的放到倒数第三的位置
        for (int j = 0; j < arr.length - 1 - 2; j++) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第三趟排序之后: " + Arrays.toString(arr));

        //第四趟排序, 第四大的放到倒数第四的位置
        for (int j = 0; j < arr.length - 1 - 3; j++) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第四趟排序之后: " + Arrays.toString(arr));
*/

    }

}
