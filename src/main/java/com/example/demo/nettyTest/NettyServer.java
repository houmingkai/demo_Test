package com.example.demo.nettyTest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Netty服务端   Netty 封装了 JDK 的 NIO
 *
 */
public class NettyServer {


    /**
     *   创建一个引导类，然后给他指定线程模型，IO模型，连接读写处理逻辑，绑定端口之后，服务端就启动起来了。
     * @param args
     */
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();  //对应 IOServer.java 中的接受新连接线程，主要负责创建新连接
        NioEventLoopGroup worker = new NioEventLoopGroup();  //对应 IOServer.java 中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理

        ServerBootstrap serverBootstrap = new ServerBootstrap();//创建引导类
        serverBootstrap
                .group(boss, worker)                    //1.指定线程模型
                .channel(NioServerSocketChannel.class)  //2.指定IO模型,NioServerSocketChannel(对应BIO中的ServerSocket)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {  //NioSocketChannel(对应BIO中的Socket)
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .bind(8000);
    }
}
