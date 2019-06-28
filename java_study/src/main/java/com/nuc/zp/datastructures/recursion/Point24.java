package com.nuc.zp.datastructures.recursion;

import java.util.*;

/**
 * 题目描述
 * 计算 24 点是一种扑克牌益智游戏，随机抽出 4 张扑克牌，通过加 (+) ，减 (-) ，乘 ( * ),  除 (/) 四种运算法则计算得到整数 24 ，
 * 本问题中，扑克牌通过如下字符或者字符串表示，其中，小写 joker 表示小王，大写 JOKER 表示大王：
 * 3 4 5 6 7 8 9 10 J Q K A 2 joker JOKER
 * 本程序要求实现：输入 4 张牌，输出一个算式，算式的结果为 24 点。
 * 详细说明：
 * 1. 运算只考虑加减乘除运算，没有阶乘等特殊运算符号， 友情提醒，整数除法要当心 ；
 * 2. 牌面 2~10 对应的权值为 2~10, J 、 Q 、 K 、 A 权值分别为为 11 、 12 、 13 、 1 ；
 * 3. 输入 4 张牌为字符串形式，以 一个空格 隔开，首尾无空格；如果输入的 4 张牌中包含大小王，则输出字符串“ ERROR ”，表示无法运算；
 * <p>
 * 4. 输出的算式格式为 4 张牌通过四个运算符相连， 中间无空格 ， 4张牌出现顺序任意，只要结果正确；
 * 5.输出算式的运算顺序从左至右，不包含括号 ，如 1+2+3*4的结果为 24
 * 6.如果存在多种算式都能计算得出 24 ，只需输出一种即可，如果无法得出 24 ，则输出“ NONE ”表示无解。
 * <p>
 * 输入描述:
 * 输入4张牌为字符串形式，以一个空格隔开，首尾无空格；
 * 输出描述:
 * 如果输入的4张牌中包含大小王，则输出字符串“ERROR”，表示无法运算；
 * 示例1
 * 输入
 * A A A A
 * 输出
 * NONE
 */
public class Point24 {

    static String[] fuhao = new String[]{"+", "-", "*", "/"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String result = jisuan(s);
        if (!"".equals(result)) {
            System.out.println(result);
        }
    }

    public static String jisuan(String str) {
        if ("".equals(str)) {
            return "NONE";
        }
        if (str.contains("joker") || str.contains("JOKER")) {
            return "ERROR";
        }
        String test2 = str.replaceAll("J", "11").replaceAll("Q", "12").replaceAll("K", "13").replaceAll("A", "1");

        String[] split = test2.split(" ");


        return test(getStrSet(split)) ? "" : "NONE";
    }

    public static Set<String> getStrSet(String[] split) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < split.length; i++) {
            for (int j = 0; j < split.length; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < split.length; k++) {

                    if (i == k || j == k) {
                        continue;
                    }

                    for (int l = 0; l < split.length; l++) {
                        if (i == l || j == l || k == l) {
                            continue;
                        }
                        set.add(split[i] + " " + split[j] + " " + split[k] + " " + split[l]);
                    }
                }
            }
        }
        return set;
    }

    public static String replace(String str) {
        return str.replaceAll("11", "J").replaceAll("12", "Q").replaceAll("13", "K").replaceAll("1", "A");
    }


    public static boolean test(Set<String> set) {
        boolean flag = false;

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            String[] split = next.split(" ");
            double total = 0;
            for (int i = 0; i < fuhao.length; i++) {
                for (int j = 0; j < fuhao.length; j++) {
                    for (int k = 0; k < fuhao.length; k++) {

                        total = jisuan(Integer.parseInt(split[0]), Integer.parseInt(split[1]), fuhao[i]);
                        total = jisuan(total, Integer.parseInt(split[2]), fuhao[j]);
                        total = jisuan(total, Integer.parseInt(split[3]), fuhao[k]);
                        if (total == 24) {
                            flag = true;
                            System.out.printf("%s%s%s%s%s%s%s\n", replace(split[0]), fuhao[i], replace(split[1]), fuhao[j], replace(split[2]), fuhao[k], replace(split[3]));
                            break;
                        }
                        total = 0;

                    }
                    if (flag) {
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (flag) {
                break;
            }

        }
        return flag;
    }

    public static double jisuan(double num1, double num2, String s) {
        if (s.equals("+")) {
            return num1 + num2;
        } else if (s.equals("-")) {
            return num1 - num2;
        } else if (s.equals("*")) {
            return num1 * num2;
        } else if (s.equals("/")) {
            return ((1.0 * num1 / num2));
        }
        return 0;
    }
}
