package cn.swallowff.modules.demo.io.tio.usecase.common;

import java.io.Serializable;
import java.util.Map;

/**
 * @author shenyu
 * @create 2019/7/19
 */
public class WebSocketRequest implements Serializable {
    private String action;
    private String contentType;
    private Map<String,Object> body;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
