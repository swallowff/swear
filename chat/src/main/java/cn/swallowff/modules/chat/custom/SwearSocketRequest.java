package cn.swallowff.modules.chat.custom;

import java.util.Map;

/**
 * @author Administrator
 * @description
 * @create 2019/7/22
 */
public class SwearSocketRequest {
    private String requestType;   //请求类型
    private Map<String,Object> body;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
