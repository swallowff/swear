package cn.swallowff.modules.websocket.nio.channel.demo.client;

import cn.swallowff.modules.websocket.nio.AbstractSelectionKeyHandler;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author shenyu
 * @create 2019/7/17
 */
public class ClientSelectionKeyHandler extends AbstractSelectionKeyHandler {


    @Override
    public void onConnectable(SelectionKey key, Selector selector) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();

        if (sc.finishConnect()) {
            System.out.println("connect server success");
            sc.register(selector, SelectionKey.OP_READ);
//            write("发送心跳包");
        } else
            System.exit(1);// 连接失败，进程退出
        }
}
