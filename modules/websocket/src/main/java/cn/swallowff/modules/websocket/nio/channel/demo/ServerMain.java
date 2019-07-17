package cn.swallowff.modules.websocket.nio.channel.demo;

/**
 * @author Administrator
 * @description
 * @create 2019/7/17
 */

public class ServerMain {

    public static void main(String[] args) {
        int port = 8010;
        MultiplexerTimeServer server = new MultiplexerTimeServer(port);
        new Thread(server,"timeserver-001").start();
    }
}