package cn.swallowff.modules.core.modules.system.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author shenyu
 * @create 2019/7/11
 */
public class GenCodeDto {
    @NotBlank(message = "请选择table")
    private String table;
    private String entityClassName;
    private String title;
    private String tablePrefix;
    private String rootModule;

    private String javaRoot;
    private String relaClassPath;
    private String mapperRoot;
    private String relaMapperPath;
    private String htmlRoot;
    private String relaHtmlPath;
    private String jsRoot;
    private String relaJsPath;
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

    public String getRelaClassPath() {
        return relaClassPath;
    }

    public void setRelaClassPath(String relaClassPath) {
        this.relaClassPath = relaClassPath;
    }

    public String getRelaMapperPath() {
        return relaMapperPath;
    }

    public void setRelaMapperPath(String relaMapperPath) {
        this.relaMapperPath = relaMapperPath;
    }

    public String getRelaHtmlPath() {
        return relaHtmlPath;
    }

    public void setRelaHtmlPath(String relaHtmlPath) {
        this.relaHtmlPath = relaHtmlPath;
    }

    public String getRelaJsPath() {
        return relaJsPath;
    }

    public void setRelaJsPath(String relaJsPath) {
        this.relaJsPath = relaJsPath;
    }

    public boolean isForceCover() {
        return forceCover;
    }

    public void setForceCover(boolean forceCover) {
        this.forceCover = forceCover;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public String getJavaRoot() {
        return javaRoot;
    }

    public void setJavaRoot(String javaRoot) {
        this.javaRoot = javaRoot;
    }

    public String getMapperRoot() {
        return mapperRoot;
    }

    public void setMapperRoot(String mapperRoot) {
        this.mapperRoot = mapperRoot;
    }

    public String getHtmlRoot() {
        return htmlRoot;
    }

    public void setHtmlRoot(String htmlRoot) {
        this.htmlRoot = htmlRoot;
    }

    public String getJsRoot() {
        return jsRoot;
    }

    public void setJsRoot(String jsRoot) {
        this.jsRoot = jsRoot;
    }
}
