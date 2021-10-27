package com.nuc.zp.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel2Demo {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("file01.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("file02.txt");

        FileChannel fileInputStreamChannel = fileInputStream.getChannel();
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            byteBuffer.clear();//清空Buffer
            int read = fileInputStreamChannel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            fileOutputStreamChannel.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }


}
