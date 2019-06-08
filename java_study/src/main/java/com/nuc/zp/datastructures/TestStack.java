package com.nuc.zp.datastructures;

import java.util.Stack;

/**
 * auther: ZP
 * time:   2019/6/8 19:07
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        while (!stack.empty()){
            System.out.println(stack.pop());
        }
    }
}
