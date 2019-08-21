package com.zuofei.netty.https;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @content:
 * @auther: 左飞
 * @date: 2019/8/21 14:51
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        //事件循环组
        EventLoopGroup boss = new NioEventLoopGroup(); //异步io,就是一个死循环 获取连接，boos接收到连接之后移交给worker
        EventLoopGroup worker = new NioEventLoopGroup();

        try {

          ServerBootstrap serverBootstrap = new ServerBootstrap();
          serverBootstrap.group(boss,worker).channel(NioServerSocketChannel.class).childHandler(new ServerInitializer());
          ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
          channelFuture.channel().closeFuture().sync();
      }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
      }
    }
}
