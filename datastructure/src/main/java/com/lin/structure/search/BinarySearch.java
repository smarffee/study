package com.lin.structure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 前提：数组有序
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};

        List<Integer> index = binarySearch2(arr, 0, arr.length - 1, 1000);

        System.out.println(index);
    }

    /**
     *
     * @param arr 数组
     * @param left 左索引
     * @param right 右索引
     * @param findValue 要查找的数值
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findValue) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (midValue > findValue) {
            return binarySearch(arr, left, mid - 1, findValue);
        } else if (midValue < findValue) {
            return binarySearch(arr, mid + 1, right, findValue);
        } else {
            return mid;
        }

    }



    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findValue) {
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (midValue > findValue) {
            return binarySearch2(arr, left, mid - 1, findValue);
        } else if (midValue < findValue) {
            return binarySearch2(arr, mid + 1, right, findValue);
        } else {
            List<Integer> result = new ArrayList<>();
            //中间的那个
            result.add(mid);

            //向左找
            int leftIndex = mid - 1;
            while (true) {
                if (leftIndex < 0 || arr[leftIndex] != findValue) {
                    break;
                }
                result.add(leftIndex);
                leftIndex--;
            }

            //向右找
            int rightIndex = mid + 1;
            while (true) {
                if (rightIndex > arr.length - 1 || arr[rightIndex] != findValue) {
                    break;
                }
                result.add(rightIndex);
                rightIndex++;
            }

            return result;
        }

    }

}
