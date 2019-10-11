package cn.swallowff.modules.demo.io.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * @author shenyu
 * @create 2019/7/17
 */
public interface SelectionKeyHandler {

    void handleSelectionKey(SelectionKey selectionKey,Selector selector,MessageProcesser messageProcesser) throws IOException;

    void onAcceptable(SelectionKey key, Selector selector) throws IOException;

    void onConnectable(SelectionKey key,Selector selector) throws IOException;

    void onReadable(SelectionKey key, Selector selector, MessageProcesser messageProcesser) throws IOException;

    void onWritable() throws IOException;

}
