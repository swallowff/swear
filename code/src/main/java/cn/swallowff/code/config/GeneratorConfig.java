package cn.swallowff.code.config;

import cn.swallowff.code.FileType;

import java.sql.Connection;
import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/10
 */
public class GeneratorConfig {
    private String moduleLocation;  //web  |  modules.core
    private String javaLocation;    //cn.swallowff.modules.system
    private String mapperLocation;  //mapper.system
    private String htmlLocation;    //WEB-INF.pages.admin.system
    private String jsLocation;      //static.module.admin.system
    private Connection connection;
    private String tableName;
    private List<FileType> genFiles;
    private String templatePath;
    private String tablePrefix;
    private String title;

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
}
