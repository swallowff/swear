package cn.swallowff.admin.modules.system.manager.logmgr;

public enum LogSucceed {

    SUCCESS(true),
    FAIL(false);

    Boolean code;

    LogSucceed(Boolean code) {
        this.code = code;
    }

    public Boolean getCode() {
        return code;
    }

    public void setCode(Boolean code) {
        this.code = code;
    }
}