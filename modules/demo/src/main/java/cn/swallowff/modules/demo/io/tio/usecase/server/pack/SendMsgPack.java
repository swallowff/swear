package cn.swallowff.modules.demo.io.tio.usecase.server.pack;

import java.io.Serializable;

/**
 * @author Administrator
 * @description
 * @create 2019/7/20
 */
public class SendMsgPack implements Serializable {
    private String to;
    private String type;
    private Object content;

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
