package cn.swallowff.admin.cmomon.constant.states;

import cn.swallowff.admin.cmomon.excepiton.AbstractBaseExceptionEnum;

public enum ResponseState implements AbstractBaseExceptionEnum {
    /**
     * 通用状态
     */
    SUCCESS(0, "操作成功"),
    FAIL(-1, "操作失败"),
    SYSTEM_ERROR(502, "系统错误"),
    UNKNOWN_ERROR(500, "未知的错误"),
    EMPTY_DATA(400, "数据为空"),
    ILLEGAL_PARAMS(401, "参数错误"),

    /**
     * 权限认证
     */
    SESSION_TIME_OUT(1001, "会话状态已过期"),
    INCORRECT_PASSWORD(1003, "密码错误"),
    NO_PERMISSIONS(1004, "暂无权限"),
    INVALID_KAPTCHA(1005, "验证码错误"),

    /**
     * 参数格式
     */
    ILLEGAL_FORMAT_EMAIL(1050, "邮箱格式不合法"),
    ILLEGAL_FORMAT_PHONE(1051, "手机号不合法"),

    /**
     * 用户管理
     */
    USER_ACCOUNT_EXISTS(1100, "用户名重复了哟，换一个试试吧"),;

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
