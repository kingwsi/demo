package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * description:  <br>
 * date: 2022/1/12 16:19 <br>
 * author: ws <br>
 */
public class ChatClient {

    public void startClient(String name) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8001));

        // 接收服务器返回消息
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        new Thread(new ClientThread(selector)).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            if (msg.length() > 0) {
                socketChannel.write(StandardCharsets.UTF_8.encode(name + ":"+msg));
            }
        }
    }
}
