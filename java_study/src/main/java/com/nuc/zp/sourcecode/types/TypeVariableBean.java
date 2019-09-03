package com.nuc.zp.sourcecode.types;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

public class TypeVariableBean<K extends InputStream & Closeable, V> {
    // K 的上边界是 InputStream
    K key;
    // 没有指定的话 ，V 的 上边界 属于 Object
    V value;
    // 不属于 TypeTypeVariable
    V[] values;
    String str;
    List<K> kList;
    public static void main(String[] args) throws NoSuchFieldException {
        Field fk = TypeVariableBean.class.getDeclaredField("key");
        TypeVariable keyType = (TypeVariable) fk.getGenericType();
        for (Type type : keyType.getBounds()) {
            System.out.println(type);
        }
        System.out.println("---------------------");
        for (AnnotatedType annotatedType : keyType.getAnnotatedBounds()) {
            System.out.println(annotatedType +"----"+annotatedType.getType());
        }
        System.out.println("***********************");
        System.out.println(keyType.getName());
        System.out.println(keyType.getGenericDeclaration());
    }
}