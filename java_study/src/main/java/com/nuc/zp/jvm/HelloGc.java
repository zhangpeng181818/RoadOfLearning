package com.nuc.zp.jvm;

public class HelloGc {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("********HelloGc");
//        Thread.sleep(Integer.MAX_VALUE);


        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY(-Xms)：" + totalMemory + "  " + totalMemory / 1024 / 1024);
        System.out.println("Max_MEMORY(-xMX):" + maxMemory + " " + maxMemory / 1024 / 1024);
        byte[] bytes = new byte[50 * 1024 * 1024];
    }
}
