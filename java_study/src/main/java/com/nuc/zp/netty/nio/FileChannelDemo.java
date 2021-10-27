package com.nuc.zp.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        write();
        read();


    }

    public static void write() throws IOException {
        byte[] bytes = new String("hello").getBytes();

        FileOutputStream fileOutputStream = new FileOutputStream("file01.txt");

        //通过fileOutputStream 获取对应的Channel
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        //将bytes放入Buffer
        byteBuffer.put(bytes);

        byteBuffer.flip();
        //将buffer写入通道
        fileChannel.write(byteBuffer);
        fileChannel.close();
    }

    public static void read() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("file01.txt");
        FileChannel channel = fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(fileInputStream.available());

        //将通道中的数据读到buffer
        channel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));

        channel.close();
    }
}
