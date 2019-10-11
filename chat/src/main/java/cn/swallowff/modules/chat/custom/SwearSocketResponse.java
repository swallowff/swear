package cn.swallowff.modules.chat.custom;

import cn.swallowff.common.json.JacksonUtil;

/**
 * @author Administrator
 * @description
 * @create 2019/7/22
 */
public class SwearSocketResponse {
    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_TIPS = "SUCCESS";
    private static final int ERROR_CODE = -1;
    private static final String ERROR_TIPS = "ERROR";

    private Integer code;
    private String msg;
    private Object data;

    public SwearSocketResponse() {
    }

    public SwearSocketResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static SwearSocketResponse newSuccess(){
        return new SwearSocketResponse(SUCCESS_CODE,SUCCESS_TIPS);
    }

    public static SwearSocketResponse newError(){
        return new SwearSocketResponse(ERROR_CODE,ERROR_TIPS);
    }

    public SwearSocketResponse putData(Object data){
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toJson(){
        return JacksonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
