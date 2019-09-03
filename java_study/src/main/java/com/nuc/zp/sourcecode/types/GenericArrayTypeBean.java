package com.nuc.zp.sourcecode.types;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

public class GenericArrayTypeBean<T> {
    // 属于 GenericArrayType
    public List<String>[] pTypeArray;
    // 属于 GenericArrayType
    T[] vTypeArray;
    // 不属于 GenericArrayType
    List<String> list;
    // 不属于 GenericArrayType
    String[] strings;
    // 不属于 GenericArrayType
    Person[] ints;

    public static void main(String[] args) {
        testGenericArrayType();
    }

    public void test(List<String>[] pTypeArray, T[] vTypeArray,
                     List<String> list, String[] strings, Person[] ints) {
    }

    public static void testGenericArrayType() {
        for (Method method : GenericArrayTypeBean.class.getDeclaredMethods()) {
            System.out.println(method);
        }
        System.out.println("--------------------------------");
        Method method = GenericArrayTypeBean.class.getDeclaredMethods()[1];
        System.out.println(method);
        // public void test(List<String>[] pTypeArray, T[]
        // vTypeArray,List<String> list, String[] strings, Person[] ints)
        Type[] types = method.getGenericParameterTypes(); // 这是 Method 中的方法
        for (Type type : types) {
            System.out.println(type + "--------" + (type instanceof GenericArrayType));// 依次输出true，true，false，false，false
        }
    }
}
