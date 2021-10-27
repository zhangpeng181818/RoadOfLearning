package com.nuc.zp.juc.thread;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadSafeTest {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (; ; ) {
                int a = 1;
                int b = 0;
                System.out.println(Thread.currentThread().getName());
                int c = a/b;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    });
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName()+"---1--"+e.getMessage());
            }
        });
        thread.start();
        System.out.println("---");
    Thread threadHelp = new Thread(() -> {
        for (; ; ) {
            int a = 1;
            int b = 0;
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
        threadHelp.start();
}
}
