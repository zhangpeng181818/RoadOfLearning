package com.nuc.zp.netty.nio.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public NioServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();

        selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动成功。。。");
    }

    public void listen() throws IOException {
        while (true) {
            if (selector.select() == 0) {
                System.out.println("服务器等待10s，无连接");
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("服务器收到新的连接:" + socketChannel.getRemoteAddress().toString());

                }

                if (selectionKey.isReadable()) {
                    readData(selectionKey);
                }
                keyIterator.remove();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.listen();

    }

    private void readData(SelectionKey selectionKey) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) selectionKey.channel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count > 0) {
                byte[] dst = new byte[count];
                buffer.flip();
                buffer.get(dst, 0, count);
                String msg = new String(dst);
                System.out.println("服务器收到：" + msg);
                sendMsgToOthers(channel, msg);
            } else if (count == -1) {
                close(channel, selectionKey);
            }
        } catch (IOException e) {
            close(channel, selectionKey);
        }


    }

    private void sendMsgToOthers(Channel self, String msg) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            Channel socketChannel = key.channel();
            if (socketChannel instanceof SocketChannel && self != socketChannel) {
                ((SocketChannel) socketChannel).write(ByteBuffer.wrap(msg.getBytes()));
            }
        }
    }

    private void close(SocketChannel channel, SelectionKey selectionKey) {
        try {
            System.out.println(channel.getRemoteAddress().toString() + "  离线了");
            selectionKey.cancel();
            channel.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
