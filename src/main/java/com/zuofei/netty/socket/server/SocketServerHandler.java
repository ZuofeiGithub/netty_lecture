package com.zuofei.netty.socket.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @content:
 * @auther: 左飞
 * @date: 2019/8/21 16:07
 */
public class SocketServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+":"+msg);
        ctx.channel().writeAndFlush("from server: "+ UUID.randomUUID());
        Thread.sleep(4000);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
