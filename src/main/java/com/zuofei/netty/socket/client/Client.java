package com.zuofei.netty.socket.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @content:
 * @auther: 左飞
 * @date: 2019/8/21 16:12
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup client = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(client).channel(NioSocketChannel.class).handler(new ClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost",8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            client.shutdownGracefully();
        }
    }
}
