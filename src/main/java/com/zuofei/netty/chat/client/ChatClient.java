package com.zuofei.netty.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @content:
 * @auther: 左飞
 * @date: 2019/8/21 17:24
 */
public class ChatClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup client = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(client).channel(NioSocketChannel.class).handler(new ChatClientInitializer());
            Channel channel = bootstrap.connect("localhost",8899).sync().channel();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        }finally {
            client.shutdownGracefully();
        }
    }
}
