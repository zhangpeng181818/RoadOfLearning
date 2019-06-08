package com.nuc.zp.datastructures;

/**
 * auther: ZP
 * time:   2019/6/7 10:22
 * description:棋盘存储
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始二维数组11*11
        //0：没有棋子 1：黑棋 2：白棋
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出
//        for (int i = 0; i < 11; i++) {
//            for (int j = 0; j < 11; j++) {
//                System.out.print(chessArr1[i][j]+" ");
//            }
//            System.out.println();
//        }
        System.out.println("--------------原始二维数组--------------------");
        int sum = 0;
        for (int[] ch : chessArr1) {
            for (int item : ch) {
                System.out.printf("%d\t", item);
                if (item != 0) {
                    sum++;
                }
            }
            System.out.println();
        }

        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println("--------------稀疏数组--------------------");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t%d\n",i,sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        System.out.println("--------------稀疏数组恢复原始二维数组--------------------");
        int[][] sourceChessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            sourceChessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        for (int i = 0; i < sourceChessArr.length; i++) {
            for (int j = 0; j < sourceChessArr[i].length; j++) {
                System.out.printf("%d\t",sourceChessArr[i][j]);
            }
            System.out.println();
        }
    }
}
