package net.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * description:  Netty 客户端示例<br>
 * date: 2022/1/13 16:40 <br>
 * author: ws <br>
 */
public class NettyClient {
    public static void main(String[] args) {
        // 事件循环组
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        // 客户端启动对象
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler()); // 自定义处理器
                        }
                    });
            System.out.println("客户端启动成功");

            // 连接服务器
            // Netty异步模型
            ChannelFuture connect = bootstrap.connect("127.0.0.1", 8080);
            connect.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
        }

    }
}
