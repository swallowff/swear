package cn.swallowff.modules.websocket.nio.channel.demo.server;

import cn.swallowff.modules.websocket.nio.MessageProcesser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author shenyu
 * @create 2019/7/17
 */
public class MessageProcesserImpl implements MessageProcesser {
    @Override
    public void process(SocketChannel socketChannel, byte[] bytes, Charset charset) throws IOException {
        String message = new String(bytes,charset);
        System.out.println("process message success : "+message);
        String response = "服务器返回";
        if (response != null && response.trim().length() > 0) {
            byte[] rbytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(rbytes.length);
            writeBuffer.put(rbytes);
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
        }
    }
}
