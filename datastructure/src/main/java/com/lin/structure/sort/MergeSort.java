package com.lin.structure.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {

        int[] nums = { 2, 7, 8, 3, 1, 6, 9, 0, 5, 4 };

        MergeSort.sort(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));
    }

    public static int[] sort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
            sort(nums, low, mid);
            // 右边
            sort(nums, mid + 1, high);
            // 左右归并
            merge(nums, low, mid, high);
        }
        return nums;
    }

    public static void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low; // 左下界
        int j = mid + 1; // 右下界
        int index = 0;

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                temp[index++] = nums[i++];
            } else {
                temp[index++] = nums[j++];
            }
        }

        // 把左边剩余的数移入数组
        while (i <= mid)
            temp[index++] = nums[i++];

        // 把右边边剩余的数移入数组
        while (j <= high)
            temp[index++] = nums[j++];

        // 把新数组中的数覆盖nums数组
        for (int k = 0; k < temp.length; k++)
            nums[k + low] = temp[k];
    }

}
