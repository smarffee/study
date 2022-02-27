package com.lin;

import java.util.Arrays;

public class NoZeroFirst {

    public static void main(String[] args) {

        int[] arr = {2, 0, 4, 4, 0 , 8, 0, 0, 0, 1};

        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                continue;
            }

            if(arr[j] != 0) {
                j++;
                continue;
            }

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            j++;
        }

        System.out.println(Arrays.toString(arr));
    }

}
