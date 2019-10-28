package cn.swallowff.admin.modules.system.manager.logmgr;

/**
 * @author shenyu
 * @create 19-5-22
 */
public enum LogType {
    LOGIN(0, "登录日志"),
    LOGIN_FAIL(1, "登录失败日志"),
    EXIT(2, "退出日志"),
    EXCEPTION(3, "异常日志"),
    BUSSINESS(4, "业务日志");

    private Integer code;

    private String message;

    LogType(Integer code, String message) {
        this.code = code;
        this.message = message;
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
}
