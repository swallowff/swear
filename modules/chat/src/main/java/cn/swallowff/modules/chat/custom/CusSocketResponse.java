package cn.swallowff.modules.chat.custom;

import cn.swallowff.common.json.JacksonUtil;

/**
 * @author Administrator
 * @description
 * @create 2019/7/22
 */
public class CusSocketResponse {
    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_TIPS = "SUCCESS";
    private static final int ERROR_CODE = -1;
    private static final String ERROR_TIPS = "ERROR";

    private Integer code;
    private String msg;
    private Object data;

    public CusSocketResponse() {
    }

    public CusSocketResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static CusSocketResponse newSuccess(){
        return new CusSocketResponse(SUCCESS_CODE,SUCCESS_TIPS);
    }

    public static CusSocketResponse newError(){
        return new CusSocketResponse(ERROR_CODE,ERROR_TIPS);
    }

    public CusSocketResponse putData(Object data){
        this.data = data;
        return this;
    }

    public String toJson(){
        return JacksonUtil.toJson(this);
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

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
