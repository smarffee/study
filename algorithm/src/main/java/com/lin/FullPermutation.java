package com.lin;

import java.util.Arrays;

public class FullPermutation {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};

        fullPermutation(arr, 0);

    }

    public static void fullPermutation(int[] arr, int start) {

        if (start == arr.length - 1) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            {
                int temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
            }

            fullPermutation(arr, start + 1);

            {
                int temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
            }
        }

    }

}
