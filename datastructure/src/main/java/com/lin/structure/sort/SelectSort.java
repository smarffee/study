package com.lin.structure.sort;

import java.util.Arrays;

/**
 * 选择排序
 * O(n^2)
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        System.out.println("原始数组 ：" + Arrays.toString(arr));
        selectSort(arr);
    }

    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            //每一轮排序执行的算法
            int minNumIndex = i;
            for (int j = minNumIndex + 1; j < arr.length; j++) {
                //找出最小数的下表索引
                if (arr[minNumIndex] > arr[j]) {
                    minNumIndex = j;
                }
            }

            if (minNumIndex != i) { //初始时，minNumIndex 就是 i
                int temp = arr[minNumIndex];
                arr[minNumIndex] = arr[i];
                arr[i] = temp;
            }

            System.out.println("第" + (i + 1) + "轮以后：" + Arrays.toString(arr));
        }


/*
        //第1轮
        int minNumIndex = 0;
        for (int j = minNumIndex + 1; j < arr.length; j++) {
            //找出最小数的下表索引
            if (arr[minNumIndex] > arr[j]) {
                minNumIndex = j;
            }
        }
        int temp = arr[minNumIndex];
        arr[minNumIndex] = arr[0];
        arr[0] = temp;

        System.out.println("第1轮以后：" + Arrays.toString(arr));

        //第2轮
        minNumIndex = 1;
        for (int j = minNumIndex + 1; j < arr.length; j++) {
            //找出最小数的下表索引
            if (arr[minNumIndex] > arr[j]) {
                minNumIndex = j;
            }
        }
        temp = arr[minNumIndex];
        arr[minNumIndex] = arr[1];
        arr[1] = temp;

        System.out.println("第2轮以后：" + Arrays.toString(arr));

        //第3轮
        minNumIndex = 2;
        for (int j = minNumIndex + 1; j < arr.length; j++) {
            //找出最小数的下表索引
            if (arr[minNumIndex] > arr[j]) {
                minNumIndex = j;
            }
        }
        temp = arr[minNumIndex];
        arr[minNumIndex] = arr[2];
        arr[2] = temp;

        System.out.println("第3轮以后：" + Arrays.toString(arr));
*/


    }

}
