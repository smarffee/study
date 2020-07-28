package com.lin.structure.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        int[] arr = {9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
        shellSort2(arr);
    }

    //使用逐步推导的方式来编写希尔排序 -> 移位法
    public static void shellSort2(int[] arr) {
        //增量gap, 并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap 个元素, 逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int insertValue = arr[j];

                while (j - gap >= 0 && insertValue < arr[j - gap]) {
                    //移动
                    arr[j] = arr[j - gap];
                    j -= gap;
                }

                //当退出while循环, 就给insertValue 找到插入的位置
                arr[j] = insertValue;
            }
        }

        System.out.println("排序后：" + Arrays.toString(arr));
    }

    //使用逐步推导的方式来编写希尔排序 -> 交换法
    public static void shellSort1(int[] arr) {

        int num = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有元素（共5组，每组2个元素），步长5
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] >= arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("第" + (++num) + "轮排序后：" + Arrays.toString(arr));
        }


/*
        //希尔排序的第1轮排序
        //因为第1轮排序，是将10个数据分成5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有元素（共5组，每组2个元素），步长5
            // 对距离为 5 的元素组进行排序
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] >= arr[j + 5]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第1轮排序后：" + Arrays.toString(arr));



        //希尔排序的第2轮排序
        //因为第2轮排序，是将10个数据分成 5/2 = 2 组
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] >= arr[j + 2]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第2轮排序后：" + Arrays.toString(arr));


        //希尔排序的第3轮排序
        //因为第3轮排序，是将10个数据分成 2/2 = 1 组
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] >= arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第3轮排序后：" + Arrays.toString(arr));
*/

    }

}
