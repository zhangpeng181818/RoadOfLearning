package com.nuc.zp.juc;

/**
 * auther: ZP
 * time:   2019/5/13 14:46
 */
class MyData {
    int number = 0;

    public void addTo60() {
        this.number = 60;
    }
}

/**
 * 验证volatile的可见性
 */
public class VolatileDemo {

    public static void main(String[] args) {

    }

    /**
     * volatile可以保证可见性，及时通知其他线程，主内存的值已经被修改
     */
    private static void setOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + " " + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {
        }
        System.out.println(Thread.currentThread().getName() + " " + myData.number);
    }
}
