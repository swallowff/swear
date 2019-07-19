package cn.swallowff.modules.websocket.nio.channel.demo.client;

import cn.swallowff.modules.websocket.nio.channel.demo.server.MessageProcesserImpl;

/**
 * @author Administrator
 * @description
 * @create 2019/7/17
 */
public class ClientMain {

    public static void main(String[] args) {
        int port = 8010;
        ChannelSocketClient client = new ChannelSocketClient(null,port,new ClientSelectionKeyHandler(),new ClientMessageProcesser());
        new Thread(client,"channelSocketClient").start();
    }

}
