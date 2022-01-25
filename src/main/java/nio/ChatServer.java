package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * description:  一个聊天室示例<br>
 * <a link="https://www.bilibili.com/video/BV1E64y1h7Z4?p=38&spm_id_from=pageDriver"></link>
 * date: 2022/1/12 15:37 <br>
 * author: ws <br>
 */
public class ChatServer {

    void startServer() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(8001));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Chat Server 启动成功");
        for (; ; ) {
            int channels = selector.select();
            if (channels == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                if (selectionKey.isAcceptable()) {
                    acceptOperator(socketChannel, selector);
                }

                if (selectionKey.isReadable()) {
                    readOperator(selector, selectionKey);
                }
            }
        }
    }

    // 链接成功后的处理
    private void acceptOperator(ServerSocketChannel socketChannel, Selector selector) throws IOException {
        SocketChannel channel = socketChannel.accept();

        channel.configureBlocking(false);

        channel.register(selector, SelectionKey.OP_READ);

        channel.write(StandardCharsets.UTF_8.encode("欢迎加入！"));
    }

    private void readOperator(Selector selector, SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int readLength = socketChannel.read(byteBuffer);
        String msg = "";
        if (readLength > 0) {
            // 切换读模式
            byteBuffer.flip();
            // 读取
            msg += StandardCharsets.UTF_8.decode(byteBuffer);
        }
        socketChannel.register(selector, SelectionKey.OP_READ);

        if (msg.length() > 0) {
            // 广播
            System.out.println("接收到消息(广播)... " + msg);
            castOtherClient(msg, selector, socketChannel);
        }
    }

    private void castOtherClient(String msg, Selector selector, SocketChannel socketChannel) throws IOException {
        // 获取所有已链接客户端
        Set<SelectionKey> keys = selector.keys();
        // 遍历发送
        for (SelectionKey selectionKey : keys) {
            Channel channel = selectionKey.channel();
            // 排除自己
            if (channel instanceof SocketChannel && channel != socketChannel) {
                ((SocketChannel) channel).write(StandardCharsets.UTF_8.encode(msg));
            }
        }
    }

    public static void main(String[] args) {
        try {
            new ChatServer().startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
