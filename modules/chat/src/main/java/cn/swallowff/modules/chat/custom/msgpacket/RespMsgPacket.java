package cn.swallowff.modules.chat.custom.msgpacket;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Administrator
 * @description
 * @create 2019/7/20
 */
public class RespMsgPacket {
    @JsonProperty(value = "id")
    private String from;
    private String type;
    private Object content;
    private String username;
    private String avatar;

    public RespMsgPacket(String from, String type, Object content) {
        this.from = from;
        this.type = type;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
