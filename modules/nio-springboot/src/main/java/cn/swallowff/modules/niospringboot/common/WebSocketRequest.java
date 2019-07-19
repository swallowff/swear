package cn.swallowff.modules.niospringboot.common;

/**
 * @author shenyu
 * @create 2019/7/19
 */
public class WebSocketRequest<T> {
    private String action;
    private String contentType;
    private T body;

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

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
