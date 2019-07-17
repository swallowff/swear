package cn.swallowff.modules.websocket.nio.channel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 * @description
 * @create 2019/7/17
 */
public class NioServerDemo {

    public static void main(String[] args){
        try {
            Selector selector = Selector.open();
            ServerSocketChannel server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(InetAddress.getLocalHost(),10000));
            server.configureBlocking(false);
            SelectionKey selectionKey = server.register(selector,SelectionKey.OP_ACCEPT);
            System.out.println("server open...");
            while (true){
                int num = selector.select();

                Set selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();

                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isAcceptable()) {
                        SocketChannel clientChannel = server.accept();
                        // ... deal with I/O event ...
//                        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
//                        clientChannel.read(byteBuffer);
                        System.out.println("accept new client : "+clientChannel.getRemoteAddress());
                    }
                    if (key.isReadable()){
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
                        clientChannel.read(byteBuffer);
                        System.out.println("received new messages" + byteBuffer.toString());
                    }
                    it.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
