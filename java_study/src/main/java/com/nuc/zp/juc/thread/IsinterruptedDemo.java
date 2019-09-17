package com.nuc.zp.juc.thread;

import java.util.concurrent.TimeUnit;

public class IsinterruptedDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    //
                }
            }
        };
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        //false
        System.out.printf("Thread is interrupted ? %s \n", thread.isInterrupted());
        thread.interrupt();
        //true
        System.out.printf("Thread is interrupted ? %s \n", thread.isInterrupted());
    }
}
