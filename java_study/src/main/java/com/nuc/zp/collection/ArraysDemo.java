package com.nuc.zp.collection;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArraysDemo {
    public static void main(String[] args) {
//        demo();
//        demo2();
//        demo3();
        demo4();
        demo5();
    }

    /**
     * 错误示例
     */
    private static void demo() {
        int[] arr = {1, 2, 3};
        List list = Arrays.asList(arr);
        System.out.println(list.size());//1
    }

    /**
     * 错误示例
     */
    private static void demo2() {
        String[] arr = {"欢迎", "关注", "Java"};
        List<String> list = Arrays.asList(arr);
        arr[1] = "爱上";
        list.set(2, "我");
        System.out.println(Arrays.toString(arr));//[欢迎, 爱上, 我]
        System.out.println(list.toString());//[欢迎, 爱上, 我]
    }

    /**
     * 错误示例
     */
    private static void demo3() {
        String[] arr = {"欢迎", "关注", "Java"};
        List<String> list = Arrays.asList(arr);
        list.add("新增");
        list.remove("关注");
        /**
         * Exception in thread "main" java.lang.UnsupportedOperationException
         * 	at java.util.AbstractList.add(AbstractList.java:148)
         * 	at java.util.AbstractList.add(AbstractList.java:108)
         * 	at com.nuc.zp.collection.ArraysDemo.demo3(ArraysDemo.java:31)
         * 	at com.nuc.zp.collection.ArraysDemo.main(ArraysDemo.java:10)
         */
        System.out.println(list.toString());
    }

    /**
     * 正确使用
     * spring
     */
    private static void demo4() {
        int[] a = {1, 2, 3};
        List list = CollectionUtils.arrayToList(a);
        System.out.println(list);
    }

    /**
     * 正确使用
     * Java8
     */
    private static void demo5() {
        int[] intArray = {1, 2, 3};
        List<Integer> list = Arrays.stream(intArray).boxed().collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 遍历转换
     */
    private static void demo6() {
        Integer intArray[] = {1, 2, 3};
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer i : intArray) {
            list.add(i);
        }
    }

    /**
     * 工具类
     */
    private static void demo7() {
        ArrayList<Object> list = new ArrayList<>();
        Collections.addAll(list, "welcome", "to", "china");
    }

    /**
     * 两个集合类结合
     * 将Arrays.asList返回的集合作为ArrayList的构造参数
     */
    private static void demo8() {
        ArrayList arrayList = new ArrayList(Arrays.asList("welcome", "to", "china"));
    }

}
