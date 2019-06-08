package com.nuc.zp.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
//    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value) throws InterruptedException {
      readWriteLock.writeLock().lock();
      try {
          System.out.println(Thread.currentThread().getName()+"\t 正在写入:"+key);
          Thread.sleep(500);
          map.put(key, value);
          System.out.println(Thread.currentThread().getName()+"\t 写入完成");
      }finally {
          readWriteLock.writeLock().unlock();
      }
    }

    public void get(String key) throws InterruptedException {
            readWriteLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName()+"\t 正在读取：");
                Thread.sleep(500);
                Object result = map.get(key);
                System.out.println(Thread.currentThread().getName()+"\t 读取完成:"+result);
            }finally {
                readWriteLock.readLock().unlock();
            }
    }
}

/**
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 *
 * 写操作：原子+独占，整个过程必须是一个完整的统一体，
 *         中间不能被分割，被打断
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
       MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                try {
                    myCache.put(tempInt+"",tempInt+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                try {
                    myCache.get(tempInt+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
