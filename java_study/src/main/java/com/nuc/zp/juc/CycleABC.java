package com.nuc.zp.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
    int number = 1;
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    public void printA() {

        lock.lock();
        try {
            while (number != 1) {
                conditionA.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.print("A");
            }
            System.out.println();
            number++;
            conditionB.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void printB() {
        lock.lock();
        try {
            while (number != 2) {
                conditionB.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.print("B");
            }
            System.out.println();
            number++;
            conditionC.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (number != 3) {
                conditionC.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.print("C");
            }
            System.out.println();
            number = 1;
            conditionA.signal();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class CycleABC {

    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareResource.printA();
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareResource.printB();
            }
        });

        Thread threadC = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareResource.printC();
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
