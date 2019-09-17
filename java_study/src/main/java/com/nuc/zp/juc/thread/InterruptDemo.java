package com.nuc.zp.juc.thread;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {
    public static void main(String[] args) {

        //false
        System.out.println("Main Thread is interrupted ?  " + Thread.interrupted());

        Thread.currentThread().interrupt();
        //true
        System.out.println("Main Thread is interrupted ?  " + Thread.currentThread().isInterrupted());

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("I will be interrupted still");
        }
    }
}
