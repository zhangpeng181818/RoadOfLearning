package com.nuc.zp.commonsense;

public class TryCatch {

    private static String test(){
        String t = "";

        try {
            t = "try";
            return t;
        } catch (Exception e) {
            // result = "catch";
            t = "catch";
            return t;
        } finally {
            t = "finally";
        }
    }

    private static String test2(){
        String t = "";

        try {
            t = "try";
            return t;
        } catch (Exception e) {
            // result = "catch";
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    public static void main(String[] args) {

        System.out.println(test());
    }
}
