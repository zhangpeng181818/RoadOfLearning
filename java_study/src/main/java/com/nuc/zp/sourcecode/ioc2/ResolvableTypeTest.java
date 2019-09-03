package com.nuc.zp.sourcecode.ioc2;

import org.springframework.core.ResolvableType;

public class ResolvableTypeTest {
    public static void main(String[] args) {

    }
}

class A<T>{
    protected String namespace;
    public A(){
        ResolvableType t=ResolvableType.forType(getClass().getGenericSuperclass());
        namespace=t.getGeneric(0).resolve().getSimpleName();
    }
}


