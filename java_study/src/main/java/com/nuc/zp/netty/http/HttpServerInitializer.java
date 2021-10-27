package com.nuc.zp.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //处理http的编解码器
        ch.pipeline().addLast("HttpServerCodec",new HttpServerCodec());
        //业务处理
        ch.pipeline().addLast("HttpServerHandler",new HttpServerHandler());
    }
}
