package cn.swallowff.modules.core.system.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author shenyu
 * @create 2019/7/11
 */
public class GenCodeDto {
    @NotBlank(message = "请选择table")
    private String table;
    private String title;
    private String tablePrefix;
    private String rootModule;

    private String classPackage;
    private String mapperModules;
    private String htmlModules;
    private String jsModules;
    private boolean forceCover;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getRootModule() {
        return rootModule;
    }

    public void setRootModule(String rootModule) {
        this.rootModule = rootModule;
    }

    public String getClassPackage() {
        return classPackage;
    }

    public void setClassPackage(String classPackage) {
        this.classPackage = classPackage;
    }

    public String getMapperModules() {
        return mapperModules;
    }

    public void setMapperModules(String mapperModules) {
        this.mapperModules = mapperModules;
    }

    public String getHtmlModules() {
        return htmlModules;
    }

    public void setHtmlModules(String htmlModules) {
        this.htmlModules = htmlModules;
    }

    public String getJsModules() {
        return jsModules;
    }

    public void setJsModules(String jsModules) {
        this.jsModules = jsModules;
    }

    public boolean isForceCover() {
        return forceCover;
    }

    public void setForceCover(boolean forceCover) {
        this.forceCover = forceCover;
    }
}
