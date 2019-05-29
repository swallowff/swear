package cn.swallowff.modules.core.config.datasource;

/**
 * @author shenyu
 * @create 2019/4/8
 */
public enum  DataSourceType {
    READ("read"),WRITE("write");

    private String type;

    DataSourceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
