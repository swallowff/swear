package cn.swallowff.modules.core.constant.states;

import cn.swallowff.modules.core.state.AbstractState;

public enum ResponseState implements AbstractState {
    /**
     * 通用状态
     */
    SUCCESS(200,"操作成功"),
    SYSTEM_ERROR(502,"系统错误"),
    UNKNOWN_ERROR(500,"未知的错误"),
    EMPTY_DATA(400,"数据为空"),
    ILLEGAL_PARAMS(401,"参数错误"),

    ILLEGAL_FORMAT_EMAIL(1000,"邮箱格式不合法"),
    ILLEGAL_FORMAT_PHONE(1001,"手机号不合法"),
    /**
     * 用户管理
     */

    ;

    private Integer code;

    private String msg;

    ResponseState(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
