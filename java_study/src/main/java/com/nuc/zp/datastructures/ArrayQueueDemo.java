package com.nuc.zp.datastructures;

import java.util.Scanner;

/**
 * auther: ZP
 * time:   2019/6/7 14:59
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列中取数据");
            System.out.println("h(head)：查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        System.out.printf("取出的数据：%d\n", arrayQueue.getQueue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        System.out.printf("队列头：%d", arrayQueue.headQueue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    loop = false;
                    System.out.println("退出！！！");
                    break;
            }
        }
    }
}

/**
 * 使用数组模拟队列
 * 编写一个ArrayQueue类
 */
class ArrayQueue {


    private int maxSize;//表示数组的最大容量
    private int front;//队列头，指向队列第一个元素的前一个元素
    private int rear;//队列尾，指向最后一个元素
    private int[] arr;//该数据用于存放数据，模拟队列

    public ArrayQueue(int arrMaxSize) {
        arr = new int[arrMaxSize];
        this.maxSize = arrMaxSize;
        front = -1;//指向队列头部，指向队头前一个元素
        rear = -1;//指向队列尾，指向队尾的数据
    }

    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public boolean addQueue(int value) {
        if (!isFull()) {
            rear++;
            arr[rear] = value;
            return true;
        }
        return false;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        front++;
        return arr[front];
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("无数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("无数据");
        }
        return arr[front + 1];
    }
}
