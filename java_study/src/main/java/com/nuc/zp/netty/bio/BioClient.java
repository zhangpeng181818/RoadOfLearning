package com.nuc.zp.netty.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BioClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        while (true){
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 6666));
            byte[] bytes = "很高兴认识你，服务端".getBytes("utf-8");
            OutputStream outputStream = null;
            try {
                outputStream = socket.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            Thread.sleep(5000);
        }
    }
}
