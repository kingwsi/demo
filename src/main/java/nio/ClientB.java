package nio;

import java.io.IOException;

/**
 * description:  <br>
 * date: 2022/1/12 16:46 <br>
 * author: ws <br>
 */
public class ClientB {
    public static void main(String[] args) {
        try {
            new ChatClient().startClient("B");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
