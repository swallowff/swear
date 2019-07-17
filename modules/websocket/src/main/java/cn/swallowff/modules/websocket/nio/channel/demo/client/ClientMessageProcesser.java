package cn.swallowff.modules.websocket.nio.channel.demo.client;

import cn.swallowff.modules.websocket.nio.MessageProcesser;

import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author shenyu
 * @create 2019/7/17
 */
public class ClientMessageProcesser implements MessageProcesser {
    @Override
    public void process(SocketChannel socketChannel, byte[] bytes, Charset charset) {
        String message = new String(bytes,charset);
        System.out.println("client process message success : "+message);    }
}
