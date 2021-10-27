package com.nuc.zp.netty.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer可以让文件直接在内存（堆外内存）修改，操作系统不需要拷贝一次
 */
public class MapperByteBuf {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("file01.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 参数1、使用读写模式
         * 参数2、可以直接修改的起始位置
         * 参数3、是映射到内存的大小，即将1.txt的多少个字节映射到内存
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'h');
        mappedByteBuffer.put(3, (byte) '9');
        randomAccessFile.close();
    }
}
