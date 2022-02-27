package com.lin;

public class BinarySearch {

    public static void main(String[] args) {

        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(binarySearch(arr, 2));
        System.out.println(binarySearch1(arr, 2, 0, arr.length - 1));

    }

    public static int binarySearch1(int[] arr, int target, int low, int high) {

        if (target < arr[low] || target > arr[high] || low > high) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        if (arr[mid] > target) {
            return binarySearch1(arr, target, low,  mid - 1);
        } else if (arr[mid] < target) {
            return binarySearch1(arr, target, low + 1,  high);
        }

        return -1;
    }

    public static int binarySearch(int[] arr, int target) {

        int high = arr.length - 1;
        int low = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            }
        }

        return -1;
    }

}
