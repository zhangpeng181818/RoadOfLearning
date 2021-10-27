package com.nuc.zp.pf4j;

import org.pf4j.Extension;

@Extension
public class IPf4jGreetingImpl1 implements IPf4jGreeting {
    @Override
    public void sayHello() {
        System.out.println("hello, world.");
    }
}