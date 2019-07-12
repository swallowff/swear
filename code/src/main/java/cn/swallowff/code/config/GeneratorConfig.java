package cn.swallowff.code.config;

import cn.swallowff.code.FileType;

import java.sql.Connection;
import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/10
 */
public class GeneratorConfig {
    /**
     * 模块名,适用于多模块项目,指定代码生成的模块目录
     */
    private String moduleLocation;
    /**
     * .java文件完整包路径  相对于src/main/java
     * @example cn.swallowff.web.cms
     */
    private String javaLocation;
    /**
     * Mapper.xml文件包路径  相对于src/main/resources
     * @example mapper.cms
     */
    private String mapperLocation;
    /**
     * .html文件包路径   相对于src/main/webapps
     * @example WEB-INF.view.pages.modules.cms
     */
    private String htmlLocation;
    /**
     * .js文件包路径 相对于src/main/webapps
     * @example static.js.modules.cms
     */
    private String jsLocation;      //js.module.admin.system
    /**
     * 数据库连接
     */
    private Connection connection;
    /**
     * 表前缀
     * @example cms , cms_
     */
    private String tablePrefix;
    /**
     * 完整表名 如: cms_article
     */
    private String tableName;
    private List<FileType> genFiles;
    /**
     * 模板路径 相对于src/main/webapps
     * @example /templates/base/
     */
    private String templatePath;
    /**
     * 实体名称
     */
    private String title;
    /**
     * 是否强制覆盖
     */
    private boolean forceCover;

    public List<FileType> getGenFiles() {
        return genFiles;
    }

    public void setGenFiles(List<FileType> genFiles) {
        this.genFiles = genFiles;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getJavaLocation() {
        return javaLocation;
    }

    public void setJavaLocation(String javaLocation) {
        this.javaLocation = javaLocation;
    }

    public String getMapperLocation() {
        return mapperLocation;
    }

    public void setMapperLocation(String mapperLocation) {
        this.mapperLocation = mapperLocation;
    }

    public String getHtmlLocation() {
        return htmlLocation;
    }

    public void setHtmlLocation(String htmlLocation) {
        this.htmlLocation = htmlLocation;
    }

    public String getJsLocation() {
        return jsLocation;
    }

    public void setJsLocation(String jsLocation) {
        this.jsLocation = jsLocation;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getModuleLocation() {
        return moduleLocation;
    }

    public void setModuleLocation(String moduleLocation) {
        this.moduleLocation = moduleLocation;
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

    public boolean isForceCover() {
        return forceCover;
    }

    public void setForceCover(boolean forceCover) {
        this.forceCover = forceCover;
    }
}
