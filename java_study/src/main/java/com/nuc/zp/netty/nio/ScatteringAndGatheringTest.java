package com.nuc.zp.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering：将数据写入buffer时，可以采用buffer数组，依次写入
 * Gathering：从buffer读数据时，可以采用buffer数组，依次读取
 */
public class ScatteringAndGatheringTest {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(8000);

        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long read = socketChannel.read(byteBuffers);
                byteRead += read;
                System.out.println("byteRead=" + byteRead);

                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "postion=" + byteBuffer.position()
                        + "  ,limit=" + byteBuffer.limit()).forEach(System.out::println);
            }

            //将buffer翻转
            Arrays.asList(byteBuffers).forEach(Buffer::flip);

            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }
            //将所有buffer复位
            Arrays.asList(byteBuffers).forEach(ByteBuffer::clear);

            System.out.println("byteRead=" + byteRead + " ,byteWrite=" + byteWrite);
        }

    }
}
