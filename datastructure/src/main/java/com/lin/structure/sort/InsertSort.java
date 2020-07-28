package com.lin.structure.sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {

        int[] arr = {101, 34, 119, 1};

        insertSort(arr);

    }


    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            //待插入的数
            int insertValue = arr[i];
            //正在比较的数的索引
            int comparingNumIndex = i - 1;

            while (comparingNumIndex >= 0 && insertValue < arr[comparingNumIndex]) {
                arr[comparingNumIndex + 1] = arr[comparingNumIndex];
                comparingNumIndex--;
            }

            if (comparingNumIndex + 1 != i) { //insertValue 的原始位置就是 i 的位置
                arr[comparingNumIndex + 1] = insertValue;
            }

            System.out.print("第" + i + "轮插入: ");
            System.out.println(Arrays.toString(arr));
        }

    }

}
