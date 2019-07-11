package cn.swallowff.modules.core.system.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author shenyu
 * @create 2019/7/11
 */
public class GenCodeDto {
    @NotBlank(message = "请选择table")
    private String table;
    private String module;

    private String javaFileLocation;
    private String mapperFileLocation;
    private String htmlFileLocation;
    private String jsFileLocation;

    private boolean forceCover;
    private String tablePrefix;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public boolean isForceCover() {
        return forceCover;
    }

    public void setForceCover(boolean forceCover) {
        this.forceCover = forceCover;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getJavaFileLocation() {
        return javaFileLocation;
    }

    public void setJavaFileLocation(String javaFileLocation) {
        this.javaFileLocation = javaFileLocation;
    }

    public String getMapperFileLocation() {
        return mapperFileLocation;
    }

    public void setMapperFileLocation(String mapperFileLocation) {
        this.mapperFileLocation = mapperFileLocation;
    }

    public String getHtmlFileLocation() {
        return htmlFileLocation;
    }

    public void setHtmlFileLocation(String htmlFileLocation) {
        this.htmlFileLocation = htmlFileLocation;
    }

    public String getJsFileLocation() {
        return jsFileLocation;
    }

    public void setJsFileLocation(String jsFileLocation) {
        this.jsFileLocation = jsFileLocation;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
