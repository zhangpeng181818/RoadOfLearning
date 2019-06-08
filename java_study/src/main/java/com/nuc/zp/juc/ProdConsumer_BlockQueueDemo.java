package com.nuc.zp.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + " 成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + " 失败");
            }
            Thread.sleep(1000);
        }
        System.out.println("生产结束");
    }

    public void myConsum() throws Exception {
        String data = null;
        while (FLAG) {
            data = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (data == null || data.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println("2s 消费者结束");
                return;
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 消费队列" + data + " 成功");
            }
        }
    }
}

public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(1));
        new Thread(() -> {
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "生产者").start();
        new Thread(() -> {
            try {
                myResource.myConsum();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "消费者").start();

        Thread.sleep(6000);
        myResource.FLAG = false;
    }
}
