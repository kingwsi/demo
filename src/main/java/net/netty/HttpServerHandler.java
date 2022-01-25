package net.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * description:  <br>
 * date: 2021/12/31 10:13 <br>
 * author: ws <br>
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private HttpRequest request;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest) {
            request = (HttpRequest) httpObject;
            request.method();
            String uri = request.uri();
            System.out.println("Uri:" + uri);
        }
        if (httpObject instanceof HttpContent) {

            ByteBuf buf = ((HttpContent) httpObject).content();
            System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));

            ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            response.headers().add(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().add(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            channelHandlerContext.writeAndFlush(response);
        }
    }
}
