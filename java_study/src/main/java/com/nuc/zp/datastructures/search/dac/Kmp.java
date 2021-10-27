package com.nuc.zp.datastructures.search.dac;

import java.util.Arrays;

public class Kmp {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index);
    }

    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {

            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     *
     *       A B C D A B D
     *       0 1 2 3 4 5 6
     * next  0 0 0 0 1 2 0
     * 1.D【j】的意义:P串的前j+1个字母,即P【0】~P【j】所拥有的最大公共前后缀
     * 2.匹配到T【i】!=P【j】失败时,想一想P【j】是不是P串的第j+1个字母,是不是也意味着:P【0】~P【j-1】的这前j个字母已经匹配成功了
     * 3.P【0】~P【j-1】的这前j个字母的最大公共前后缀 = D【j-1】
     *
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {

            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
