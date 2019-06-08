package com.nuc.zp.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * auther: ZP
 * time:   2019/5/15 11:38
 */

class Phone implements Runnable
{
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+ "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail()");
    }

    @Override
    public void run() {
        get();
    }

    Lock lock = new ReentrantLock();

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+ "\t invoked sendSMS()");
            set();
        }finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail()");
        }finally {
            lock.unlock();
        }
    }
}

public class ReenterLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSMS();
        },"t1").start();
        new Thread(()->{
            phone.sendSMS();
        },"t2").start();

        Thread.sleep(1000);

        new Thread(phone,"t3").start();
        new Thread(phone,"t4").start();

    }
}
