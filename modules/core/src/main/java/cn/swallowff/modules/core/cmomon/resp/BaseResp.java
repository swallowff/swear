package cn.swallowff.modules.core.cmomon.resp;

import cn.swallowff.common.json.JacksonUtil;
import cn.swallowff.modules.core.constant.states.ResponseState;
import cn.swallowff.modules.core.state.AbstractState;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class BaseResp implements Serializable {
    private static final Integer SUCCESS = 200;

    private Integer code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Serializable data;

    public BaseResp(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public BaseResp(ResponseState respResponseState){
        this.code = respResponseState.getCode();
        this.message = respResponseState.getMsg();
    }

    public BaseResp putState(AbstractState respState){
        this.code = respState.getCode();
        this.message = respState.getMsg();
        return this;
    }

    public BaseResp emptyData(){
        return putState(ResponseState.EMPTY_DATA);
    }

    public BaseResp putError(){
        return putState(ResponseState.SYSTEM_ERROR);
    }

    public BaseResp paramsError(){
        return putState(ResponseState.SUCCESS);
    }

    public BaseResp putSuccess(){
        return putState(ResponseState.SUCCESS);
    }

    public static BaseResp newSuccess(){
        return new BaseResp(ResponseState.SUCCESS);
    }

    public boolean success(){
        return SUCCESS == this.getCode();
    }

    public static BaseResp newError(){
        return new BaseResp(ResponseState.SYSTEM_ERROR);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Serializable getData() {
        return data;
    }

    public BaseResp setData(Serializable data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
