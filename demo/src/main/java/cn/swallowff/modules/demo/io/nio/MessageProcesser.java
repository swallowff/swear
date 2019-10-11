package cn.swallowff.modules.demo.io.nio;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public interface MessageProcesser {

    void process(SocketChannel socketChannel, byte[] bytes, Charset charset) throws IOException;

}
