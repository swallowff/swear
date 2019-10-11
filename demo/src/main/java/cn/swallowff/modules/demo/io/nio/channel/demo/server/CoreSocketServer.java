package cn.swallowff.modules.demo.io.nio.channel.demo.server;

import cn.swallowff.modules.demo.io.nio.MessageProcesser;
import cn.swallowff.modules.demo.io.nio.SelectionKeyHandler;

import java.io.IOException;
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
public class CoreSocketServer implements Runnable {

    private Selector selector;

    private volatile boolean stop;

    private ServerSocketChannel servChannel;

    private SelectionKeyHandler selectionKeyHandler;

    private MessageProcesser messageProcesser;

    /**
     * 初始化多路复用器、绑定监听端口
     *
     * @param port
     */
    public CoreSocketServer(int port, SelectionKeyHandler selectionKeyHandler, MessageProcesser messageProcesser) {
        try {
            this.selectionKeyHandler = selectionKeyHandler;
            this.messageProcesser = messageProcesser;
            // 创建多路复用器Selector、ServerSocketChannel
            selector = Selector.open();
            servChannel = ServerSocketChannel.open();
            // 配置为非阻塞模式
            servChannel.configureBlocking(false);
            // 绑定端口，将backlog设置为1024
            servChannel.socket().bind(new InetSocketAddress(port), 1024);
            // 系统资源初始化成功后，把serverSocketChannel注册到Selector，监听SelectionKey.OP_ACCEPT操作位
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The socket server started in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                //循环体重遍历selector，超时时间为1s。
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        selectionKeyHandler.handleSelectionKey(key,selector,messageProcesser);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null)
                                key.channel().close();
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null)
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    // 发送消息异步发给客户端。
    private void doWrite(SocketChannel channel, String response)
            throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
