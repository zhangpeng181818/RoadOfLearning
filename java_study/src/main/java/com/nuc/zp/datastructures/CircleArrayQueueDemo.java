package com.nuc.zp.datastructures;

import java.util.Scanner;

/**
 * auther: ZP
 * time:   2019/6/8 10:22
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(5);
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
                    if(!arrayQueue.addQueue(value)){
                        System.out.println("添加失败！");
                    }
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
 * 数组模拟环形队列
 * 1、front指向队列的第一个元素，也就是arr[front]就是
 * 队列的第一个元素，front的初始值=0
 * 2、rear指向队列的最后一个元素的后一个位置。
 * 3、rear的初始值=0
 * 4、当队列满时，将队列空出一个作为约定，(rear+1)%maxSize=front
 * 5、当队列为空，rear=front
 * 6、队列中有效的个数：
 * (rear+maxSize-front)%maxSize
 */
class CircleArrayQueue {


    private int maxSize;//表示数组的最大容量
    private int front;//队列头，指向队列第一个元素的前一个元素
    private int rear;//队列尾，指向最后一个元素
    private int[] arr;//该数据用于存放数据，模拟队列
    private int length;

    public CircleArrayQueue(int arrMaxSize) {
        arr = new int[arrMaxSize];
        this.maxSize = arrMaxSize;
        front = 0;//指向队列头部，指向队列第一个元素
        rear = 0;//指向队列尾，指向队尾最后一个元素后一个元素
    }

    public int size(){
        return  length = (rear + maxSize - front) % maxSize;
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public boolean addQueue(int value) {
        if (!isFull()) {
            arr[rear] = value;
            rear = (rear + 1) % maxSize;
            return true;
        }
        return false;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("无数据");
            return;
        }
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n", (i % maxSize), arr[i % maxSize]);
        }
    }

    //显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("无数据");
        }
        return arr[front];
    }
}
