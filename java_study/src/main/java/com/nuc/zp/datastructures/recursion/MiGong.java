package com.nuc.zp.datastructures.recursion;

/**
 * 迷宫问题
 */
public class MiGong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        //使用1表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[2][2] = 1;
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }

        //使用递归回溯
        setWay2(map, 1, 1);
        System.out.println("-----------------");


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * 1、map表示地图
     * 2、i,j表示从地图哪个位置出发（1，1）
     * 3、如果小球能到map[6][5]w位置，则说明通路找到
     * 4、约定：当map[i][j]为0表示该点没有走过，当为1表示墙，2表示通路可以走，3表示该点已经走过，但是走不通
     * 5、在走迷宫时，需要确定一个策略（方法）下---》右---》上---》左，如果该点走不通，再回溯
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                //按照策略
                map[i][j] = 2;//假定该点可以走通
                if (setWay(map, i + 1, j)) {//向下
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左
                    return true;
                } else {
                    //说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    /**
     *
     * 修改找路策略，改成上--》右---》下---》左
     */
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                //按照策略
                map[i][j] = 2;//假定该点可以走通
                if (setWay2(map, i -1, j)) {//向上
                    return true;
                } else if (setWay2(map, i, j + 1)) {//向右
                    return true;
                } else if (setWay2(map, i + 1, j)) {//向下
                    return true;
                } else if (setWay2(map, i, j - 1)) {//向左
                    return true;
                } else {
                    //说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
