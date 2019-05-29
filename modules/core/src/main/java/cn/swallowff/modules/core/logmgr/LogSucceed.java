package cn.swallowff.modules.core.logmgr;

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