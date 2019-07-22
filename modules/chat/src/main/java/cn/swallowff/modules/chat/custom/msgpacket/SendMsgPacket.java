package cn.swallowff.modules.chat.custom.msgpacket;

import java.io.Serializable;

/**
 * @author Administrator
 * @description
 * @create 2019/7/20
 */
public class SendMsgPacket implements Serializable {
    private String to;
    private String type;    //消息类型
    private Object content; //消息内容

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
