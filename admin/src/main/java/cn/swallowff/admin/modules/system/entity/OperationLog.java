package cn.swallowff.admin.modules.system.entity;

import cn.swallowff.admin.cmomon.entity.BaseEntity;

/**
 * @author shenyu
 * @create 2019/3/15
 */
public class OperationLog extends BaseEntity {
    private Integer logType;  //日志类型
    private String logName;   //日志名称
    private String userId;  //用户id
    private String className;   //类名称
    private String method;  //方法名称
    private Boolean succeed;    //是否成功
    private String message; //备注

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
