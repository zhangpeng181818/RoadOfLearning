package com.nuc.zp.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class BioServer {
    public static void main(String[] args) throws IOException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(6666));
        System.out.println("服务器启动，port:" + serverSocket.getLocalPort());
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    handle(socket);
                }
            }, "Thread" + atomicInteger.getAndIncrement()).start();
        }


    }

    //和客户端通信
    public static void handle(Socket socket) {
        System.out.println("线程：" + Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        //获取输入流
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    //输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
