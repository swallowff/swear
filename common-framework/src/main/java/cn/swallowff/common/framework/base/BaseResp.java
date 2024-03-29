package cn.swallowff.common.framework.base;

import cn.swallowff.common.json.JacksonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

public class BaseResp implements Serializable {
    private static final Integer SUCCESS = ResponseState.SUCCESS.getCode();

    private Integer code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object data;

    public BaseResp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResp(ResponseState stateEnum) {
        this.code = stateEnum.getCode();
        this.msg = stateEnum.getMsg();
    }

    public BaseResp putState(IStateCode respState) {
        this.code = respState.getCode();
        this.msg = respState.getMsg();
        return this;
    }

    public BaseResp emptyData() {
        return putState(ResponseState.EMPTY_DATA);
    }

    public BaseResp putError() {
        return putState(ResponseState.FAIL);
    }

    public BaseResp putError(String tips) {
        this.code = ResponseState.FAIL.getCode();
        this.msg = tips;
        return this;
    }

    public BaseResp paramsError() {
        return putState(ResponseState.ILLEGAL_PARAMS);
    }

    public BaseResp putSuccess() {
        return putState(ResponseState.SUCCESS);
    }

    public BaseResp putSuccess(Object obj) {
        putState(ResponseState.SUCCESS);
        this.data = obj;
        return this;
    }

    public static BaseResp newSuccess() {
        return new BaseResp(ResponseState.SUCCESS);
    }

    public static BaseResp newSuccess(Object data) {
        BaseResp baseResp = new BaseResp(ResponseState.SUCCESS);
        baseResp.setData(data);
        return baseResp;
    }

    public boolean success() {
        return SUCCESS == this.getCode();
    }

    public static BaseResp newError() {
        return new BaseResp(ResponseState.SYSTEM_ERROR);
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

    public BaseResp setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
