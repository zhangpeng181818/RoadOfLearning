package com.nuc.zp.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、  线程    操作    资源类
 * 2、  判断    干活    通知
 * 3、  防止虚假唤醒机制
 */
class ShareData {

    Lock lock = new ReentrantLock();
    int value = 0;
    Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            while (value != 0) {
                condition.await();
            }
            value++;
            System.out.println(Thread.currentThread().getName() + "\t" + value);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {

        lock.lock();
        try {
            while (value == 0) {
                condition.await();
            }
            value--;
            System.out.println(Thread.currentThread().getName() + "\t" + value);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * 一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，来五轮
 */
public class ProdConsumer_TraditionDemo {

    static Object object = new Object();
    static ShareData shareData = new ShareData();


    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }, "生产者").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "消费者").start();
    }

    private static void synchronizedTest() {
        new Thread(() -> {
            for (int i = 1; i <= 5; ) {
                synchronized (object) {
                    object.notifyAll();
                    if (shareData.value > 0) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        i++;
                        shareData.value++;
                        System.out.println(Thread.currentThread().getName() + "\t " + shareData.value);
                    }
                }
            }
        }, "生产者").start();
        new Thread(() -> {

            for (int i = 1; i <= 5; ) {
                synchronized (object) {
                    object.notifyAll();
                    if (shareData.value <= 0) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        i++;
                        shareData.value--;
                        System.out.println(Thread.currentThread().getName() + "\t" + shareData.value);

                    }
                }

            }

        }, "消费者").start();
    }
}
