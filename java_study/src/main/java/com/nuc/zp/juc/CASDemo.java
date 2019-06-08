package com.nuc.zp.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * auther: ZP
 * time:   2019/5/14 9:37
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);

        System.out.println(atomicInteger.compareAndSet(10, 100));
        System.out.println(atomicInteger.compareAndSet(10, 1000));

        System.out.println(atomicInteger.getAndIncrement());

    }


}
