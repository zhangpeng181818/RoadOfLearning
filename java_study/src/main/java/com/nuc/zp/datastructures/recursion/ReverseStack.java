package com.nuc.zp.datastructures.recursion;

/**
 * 题目描述
 * 一个栈依次压入1,2,3,4,5那么从栈顶到栈底分别为5,4,3,2,1。将这个栈转置后，从栈顶到栈底为1,2,3,4,5，
 * 也就是实现了栈中元素的逆序，请设计一个算法实现逆序栈的操作，但是只能用递归函数来实现，而不能用另外的数据结构。
 * 给定一个栈Stack以及栈的大小top，请返回逆序后的栈。
 * <p>
 * 测试样例：
 * [1,2,3,4,5],5
 * 返回：[5,4,3,2,1]
 */
public class ReverseStack {


    public static void main(String[] args) {
        int[] stack = new int[]{1, 2, 3, 4, 5};
        int[] test = reverseStackRecursively2(stack, stack.length);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
    }

    /**
     * TLE
     * 运行时间：6001 ms
     */
    private static int[] reverseStackRecursively(int[] stack, int top) {

        int temp = 0;
        for (int i = 0; i < top; i++) {
            if (i != top - 1 && stack[i] < stack[i + 1]) {
                temp = stack[i];
                stack[i] = stack[i + 1];
                stack[i + 1] = temp;
            }
            reverseStackRecursively(stack, top - 1);
        }
        return stack;
    }

    /**
     * AC
     * 运行时间：147ms
     * 占用内存：12112k
     */
    private static int[] reverseStackRecursively2(int[] stack, int top) {
        int temp = 0;
        int i = stack.length - top;
        int j = top - 1;
        if (i < j) {
            temp = stack[i];
            stack[i] = stack[j];
            stack[j] = temp;
            reverseStackRecursively2(stack, top - 1);
        }
        return stack;
    }


    public int[] reverseStackRecursively3(int[] stack, int top) {
        // write code here
        reverse(stack, 0);
        return stack;
    }

    private void reverse(int[] stack, int n) {
        if (n >= stack.length / 2) {
            return;
        }
        reverse(stack, n + 1);
        int temp = stack[n];
        stack[n] = stack[stack.length - 1 - n];
        stack[stack.length - 1 - n] = temp;
        return;
    }
}

