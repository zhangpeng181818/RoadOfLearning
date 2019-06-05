package com.nuc.zp.juc.thread;

import java.util.concurrent.*;

/**
 * Array Arrays
 * Collection Collections
 * Executor   Executors
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        System.out.println(ThreadPoolExecutor.class);


//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N线程/

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                3,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.DiscardPolicy()
        );

        try {
            for (int i = 0; i <= 9; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
