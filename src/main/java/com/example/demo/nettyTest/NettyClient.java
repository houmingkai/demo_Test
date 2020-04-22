package com.example.demo.nettyTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * Netty客户端
 *
 */
public class NettyClient {


    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup();  //group对应了我们IOClient.java中 main 函数起的线程
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)      //指定线程模型
                .channel(NioSocketChannel.class)  //指定IO模型
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {  //IO处理逻辑
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();  //第一个参数可以填写 IP 或者域名，第二个参数填写的是端口号

        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
