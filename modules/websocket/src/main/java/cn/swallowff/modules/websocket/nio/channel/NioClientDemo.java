package cn.swallowff.modules.websocket.nio.channel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Administrator
 * @description
 * @create 2019/7/17
 */
public class NioClientDemo {

    public static void main(String[] args){
        try {
            SocketChannel client = SocketChannel.open();
            client.configureBlocking(true);
            client.connect(new InetSocketAddress(InetAddress.getLocalHost(),10000));
            boolean flag = true;
            int i = 0;
            while (flag){
                if (client.isConnected()){
                    ByteBuffer byteBuffer = ByteBuffer.wrap("hello".getBytes());
                    client.write(byteBuffer);
                    System.out.println("send message success");
                }
                i ++ ;
                if (i >= 10){
                    flag = false;
                }
                Thread.sleep(3000);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
