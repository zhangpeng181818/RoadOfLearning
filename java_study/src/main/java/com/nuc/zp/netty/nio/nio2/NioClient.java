package com.nuc.zp.netty.nio.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NioClient {

    private SocketChannel socketChannel;
    private Selector selector;


    public NioClient(String ip, Integer port) throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress(ip, port));
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        this.socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("客户端启动成功。。。");
    }

    public static void main(String[] args) throws IOException {
        NioClient nioClient = new NioClient("127.0.0.1", 8888);
        new Thread(() -> {
            try {
                nioClient.handServerResponse();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String msg = scanner.next();
                nioClient.sendInfo(msg.trim());
            }catch (Exception ignore){

            }
        }
    }

    private void handServerResponse() throws IOException, InterruptedException {
        while (true) {

            if (selector.select(1000) == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();

                if (selectionKey.isReadable()) {

                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    channel.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int count = channel.read(buffer);

                    if (count > 0) {
                        byte[] dst =new byte[count];
                        buffer.flip();
                        buffer.get(dst,0,count);
                        String msg = new String(dst);
                        System.out.println("客户端收到：" + msg);
                    }
                }
            }
            selectionKeyIterator.remove();

            Thread.sleep(3000);
        }
    }

    private void sendInfo(String message) throws IOException {
        message = socketChannel.getLocalAddress() + " 说：" + message;
        socketChannel.write(ByteBuffer.wrap(message.getBytes()));
    }
}
