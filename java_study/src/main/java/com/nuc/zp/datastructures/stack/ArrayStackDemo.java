package com.nuc.zp.datastructures.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("show：显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：添加数据到栈");
            System.out.println("pop：出栈");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "exit":
                    loop = false;
                    scanner.close();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    arrayStack.push(scanner.nextInt());
                    break;
                case "pop":
                    try {
                        System.out.printf("出栈的数据：%d\n",arrayStack.pop());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }

}

/**
 * 定义一个ArrayStack表示栈
 */
class ArrayStack {
    /**
     * 栈的大小
     */
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满！");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空！");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空！");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
