package com.nuc.zp.juc.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ShareData {
    BlockingQueue<String> blockingQueue = null;
    AtomicInteger atomicInteger = new AtomicInteger(1);
    volatile boolean isProduceflag = true;

    public ShareData(BlockingQueue queue) {
        this.blockingQueue = queue;
    }

    public void produce() {
        boolean offerFlag;
        String data;
        while (isProduceflag) {
            try {
                data = atomicInteger.getAndIncrement() + "";
                offerFlag = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if (offerFlag) {
                    System.out.println(Thread.currentThread().getName() + "\t 生产数据" + data + "成功");
                } else {
                    System.out.println(Thread.currentThread().getName() + "\t 生产数据失败");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consumer() {
        while (true) {
            try {
                String polldValue = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if (polldValue == null || "".equals(polldValue)) {
                    System.out.println(Thread.currentThread().getName() + "消费数据失败");
                    isProduceflag = false;
                    return;
                } else {
                    System.out.println(Thread.currentThread().getName() + "消费数据" + polldValue + "成功");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProduceConsumerOfQueue {
    public static void main(String[] args) {
        ShareData shareData = new ShareData(new LinkedBlockingQueue<>(1));
        new Thread(() -> {
            shareData.produce();
        }, "生产者").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            shareData.consumer();
        }, "消费者").start();
    }
}