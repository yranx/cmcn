package com.cmcn.controller;

import com.cmcn.netty.HttpServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务启动
 * @author ranx
 * @create 2018-11-06 21:54
 **/
public class ServerStartMain {

    private static Logger logger = LoggerFactory.getLogger(ServerStartMain.class);

    public static void main (String[] args) {
        logger.info("启动开始");
        int port;
        try {
            ApplicationContextHolder.getContext();
            if (args.length > 0) {
                port = Integer.parseInt(args[0]);
            } else {
                port = 8999;
            }
            logger.info("init port ：{}", port);
            ServerStartMain serverStartMain = new ServerStartMain();
            serverStartMain.start(port);
            logger.info("start server at: {}", port);
        } catch (Exception e) {
            logger.info("启动失败");
            logger.error("异常： {}", e);
            //终止程序
            System.exit(-1);
        }
    }

    public void start(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE,false);
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
