package com.lin.structure.sparsearray;

/**
 * 稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个二维数组 11*11
        //0: 表示没有棋子, 1: 表示黑子, 2: 表示蓝子
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        //输出原始的二维数组
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        int sparseArr[][] = convert2sparseArr(chessArr);

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为......");

        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        System.out.println();

        //将稀疏数组 --> 原始的二维数组
        int chessArr1[][] = recover(sparseArr);

        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组.....");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }

    /**
     * 稀疏数组 转 原始二维数组
     * @param sparseArr
     * @return
     */
    private static int[][] recover(int sparseArr[][]) {
        int rowNum = sparseArr[0][0];
        int colNum = sparseArr[0][1];
        int chessArr[][] = new int[rowNum][colNum];
        for (int i = 1; i < sparseArr.length; i++) {
            int row = sparseArr[i][0];
            int col = sparseArr[i][1];
            int data = sparseArr[i][2];
            chessArr[row][col] = data;
        }

        return chessArr;
    }

    /**
     * 将二维数组 转 稀疏数组
     * @param chessArr 原始二维数组
     * @return 稀疏数组
     */
    private static int[][] convert2sparseArr(int[][] chessArr) {
        //1. 获取总个数
        int num = 0;
        for (int[] row : chessArr) {
            for (int data : row) {
                if (data != 0) {
                    num++;
                }
            }
        }

        System.out.println("num = " + num);

        //2. 创建空的稀疏数组
        int sparseArr[][] = new int[num + 1][3];
        //给稀疏数组赋第一行的值
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = num;

        //3. 遍历二维数组, 将非0的值存放到 spareArr 中
        int count = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }

        return sparseArr;
    }

}
