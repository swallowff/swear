package cn.swallowff.modules.demo.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author shenyu
 * @create 2019/7/17
 */
public abstract class AbstractSelectionKeyHandler implements SelectionKeyHandler {

    @Override
    public void handleSelectionKey(SelectionKey key,Selector selector,MessageProcesser messageProcesser) throws IOException {
        if (key.isValid()) {
            // 根据操作位判断网络类型
            if (key.isAcceptable()) {
                this.onAcceptable(key,selector);
            }
            if (key.isReadable()) {
                this.onReadable(key,selector,messageProcesser);
            }
            if (key.isConnectable()){
                this.onConnectable(key,selector);
            }
            if (key.isWritable()){
                this.onWritable();
            }
        }
    }

    @Override
    public void onConnectable(SelectionKey key,Selector selector) throws IOException {

    }

    @Override
    public void onAcceptable(SelectionKey key, Selector selector) throws IOException{
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        // Add the new connection to the selector
        sc.register(selector, SelectionKey.OP_READ);
    }

    @Override
    public void onReadable(SelectionKey key, Selector selector, MessageProcesser messageProcesser) throws IOException {
        // 读取客户端的消息
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readBytes = sc.read(readBuffer);
        if (readBytes > 0) {
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            messageProcesser.process(sc,bytes,Charset.forName("UTF-8"));
        } else if (readBytes < 0) {
            //对端链路关闭
            key.cancel();
            sc.close();
        } else
            ; //读到0字节，忽略
    }

    @Override
    public void onWritable() throws IOException {

    }
}
