package cn.swallowff.modules.core.constant.states;

import cn.swallowff.modules.core.excepiton.AbstractBaseExceptionEnum;

public enum ResponseState implements AbstractBaseExceptionEnum {
    /**
     * 通用状态
     */
    SUCCESS(0,"操作成功"),
    SYSTEM_ERROR(502,"系统错误"),
    UNKNOWN_ERROR(500,"未知的错误"),
    EMPTY_DATA(400,"数据为空"),
    ILLEGAL_PARAMS(401,"参数错误"),

    ILLEGAL_FORMAT_EMAIL(1000,"邮箱格式不合法"),
    ILLEGAL_FORMAT_PHONE(1001,"手机号不合法"),
    /**
     * 用户管理
     */
    USER_ACCOUNT_EXISTS(1002,"用户名重复了哟，换一个试试吧"),

    /**
     * layui-table
     */
    LAYUI_TABLE_AJAX_SUCCESS(0,"操作成功"),


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
