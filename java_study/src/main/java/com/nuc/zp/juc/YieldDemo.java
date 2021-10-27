package com.nuc.zp.juc;

import java.util.stream.IntStream;

public class YieldDemo {

    public static void main(String[] args) {
        IntStream.range(0,2).mapToObj(YieldDemo::create).forEach(Thread::start);
    }

    private static Thread create(int index){
        return new Thread(()->{
//            if (index==0){
//                Thread.yield();
//            }X
            System.out.println(index);
        });
    }
}
