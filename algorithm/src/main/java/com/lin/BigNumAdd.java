package com.lin;

public class BigNumAdd {

    public static void main(String[] args) {
        String a = "1934";
        String b = "9123";
        System.out.println(addBigNum(a, b));
    }


    public static String addBigNum(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }

        if (b == null || b.length() == 0) {
            return a;
        }

        char[] aArr = new StringBuilder(a).reverse().toString().toCharArray();
        char[] bArr = new StringBuilder(b).reverse().toString().toCharArray();

        int length = aArr.length > bArr.length ? aArr.length + 1 : bArr.length + 1;

        int[] resultArr = new int[length];

        int jinwei = 0;
        for (int i = 0; i < length; i++) {
            int x = 0;
            if (i < aArr.length) {
                x = aArr[i] - '0';
            }

            int y = 0;
            if (i < bArr.length) {
                y = bArr[i] - '0';
            }

            int sum = x + y + jinwei;
            if (sum < 10) {
                resultArr[i] = sum;
                jinwei = 0;
            } else  {
                resultArr[i] = sum - 10;
                jinwei = 1;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = resultArr.length - 1; i >=0; i--) {
            if (i == resultArr.length - 1 && resultArr[resultArr.length - 1] == 0)
                continue;

            result.append(resultArr[i]);
        }

        return result.toString();
    }


}
