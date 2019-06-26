package com.nuc.zp.datastructures.recursion;


/**
 * 题目描述
 * <p>
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama"is a palindrome.
 * "race a car"is not a palindrome.
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * For the purpose of this problem, we define empty string as valid palindrome.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(isPalindrome2("a."));

//        System.out.println("aaF.".replaceAll("[^a-z|A-Z]",""));
    }


    public static boolean isPalindrome2(String s) {
        Boolean flag = true;
        s = s.replaceAll("\\W", "").toLowerCase();
        String[] test = s.split("");
        for (int i = 0, j = test.length - 1; i < j; i++, j--) {
            if (!test[i].equals(test[j])) {
                flag = false;
                break;
            }

        }

        return flag;

    }

    public static boolean isPalindrome(String s) {
        Boolean flag = true;
        s = s.replaceAll("\\W", "").toLowerCase();
        return isTest(s, 0, s.length(), flag);
    }

    public static boolean isTest(String s, int i, int j, Boolean flag) {

        while (flag && i < j - 1) {
            if (s.charAt(i) == s.charAt(j - 1)) {
                isTest(s, ++i, --j, flag);
            } else {
                flag = false;
            }
        }
        return flag;
    }


}
