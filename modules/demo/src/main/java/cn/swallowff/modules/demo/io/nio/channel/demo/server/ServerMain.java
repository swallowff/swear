package cn.swallowff.modules.demo.io.nio.channel.demo.server;

/**
 * @author Administrator
 * @description
 * @create 2019/7/17
 */

public class ServerMain {

    public static void main(String[] args) {
        int port = 8010;
        CoreSocketServer server = new CoreSocketServer(port,new DefaultSelectionKeyHandler(),new MessageProcesserImpl());
        new Thread(server,"channelSocketServer").start();
    }
}