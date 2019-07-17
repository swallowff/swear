package cn.swallowff.modules.websocket.nio.channel.demo;

/**
 * @author Administrator
 * @description
 * @create 2019/7/17
 */
public class ClientMain {

    public static void main(String[] args) {
        int port = 8010;
        TimeClientHandle client = new TimeClientHandle(null,port);
        new Thread(client,"client-001").start();
    }
}
