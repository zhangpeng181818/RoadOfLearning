package com.nuc.zp.juc.thread;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {

    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println("222");
                    }
                });
                return thread;
            }
        }, new ThreadPoolExecutor.DiscardOldestPolicy());


            threadPoolExecutor.execute(() -> {
                System.out.println("111");
                int c = 2 / 0;
            });


    }

}
