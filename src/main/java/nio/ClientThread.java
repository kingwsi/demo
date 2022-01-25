package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * description:  <br>
 * date: 2022/1/12 16:36 <br>
 * author: ws <br>
 */
public class ClientThread implements Runnable {

    private Selector selector;
    public ClientThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
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
                    if (selectionKey.isReadable()) {
                        readOperator(selector, selectionKey);
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
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
            System.out.println("接收到消息:" + msg);
        }
    }

}
