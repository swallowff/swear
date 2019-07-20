package cn.swallowff.modules.demo.io.tio.usecase.common;

import org.tio.core.intf.Packet;

/**
 * @author Administrator
 * @description
 * @create 2019/7/18
 */
public class HelloPacket extends Packet {
    private static final long serialVersionUID = -172060606924066412L;
    public static final int HEADER_LENGTH = 4;//消息头的长度
    public static final String CHARSET = "utf-8";
    private byte[] body;
    /**
     * @return the body
     */
    public byte[] getBody() {
        return body;
    }
    /**
     * @param body the body to set
     */
    public void setBody(byte[] body) {
        this.body = body;
    }
}
