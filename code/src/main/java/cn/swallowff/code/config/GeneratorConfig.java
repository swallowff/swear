package cn.swallowff.code.config;

import cn.swallowff.code.entity.GeneratorFile;

import java.sql.Connection;
import java.util.Set;

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
    private String javaRoot = "/src/main/java";     //默认 /src/main/java
    private String javaRelaPath = "/cn/swallowff/modules/core/modules/system";
    /**
     * Mapper.xml文件包路径  相对于src/main/resources
     * @example mapper.cms
     */
    private String mapperRoot = "/src/main/resources/mapper";   //默认 /src/main/resources/mapper
    private String mapperRelaPath = "/sys";
    /**
     * .html文件包路径   相对于src/main/webapps
     * @example WEB-INF.view.pages.modules.cms
     */
    private String htmlRoot = "/src/main/webapps/WEB-INF/view";     //默认  /src/main/webapps/WEB-INF/view
    private String htmlRelaPath = "/pages/admin/system";
    /**
     * .js文件包路径 相对于src/main/webapps
     * @example static.js.modules.cms
     */
    private String jsRoot = "/src/main/webapps/static";      //默认   /src/main/webapps/static
    private String jsRelaPath = "/modules/admin/system";
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
    /**
     * 生成文件配置
     */
    private Set<GeneratorFile> genFileSets;
    /**
     * 模板路径 相对于src/main/webapps
     * @example /templates/base/
     */
    private String templatePath;
    /**
     * 实体名称
     */
    private String title;

    private String customEntityName;
    /**
     * 是否强制覆盖
     */
    private boolean forceCover;

    public Set<GeneratorFile> getGenFileSets() {
        return genFileSets;
    }

    public void setGenFileSets(Set<GeneratorFile> genFileSets) {
        this.genFileSets = genFileSets;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
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

    public String getJavaRelaPath() {
        return javaRelaPath;
    }

    public void setJavaRelaPath(String javaRelaPath) {
        this.javaRelaPath = javaRelaPath;
    }

    public String getMapperRelaPath() {
        return mapperRelaPath;
    }

    public void setMapperRelaPath(String mapperRelaPath) {
        this.mapperRelaPath = mapperRelaPath;
    }

    public String getHtmlRelaPath() {
        return htmlRelaPath;
    }

    public void setHtmlRelaPath(String htmlRelaPath) {
        this.htmlRelaPath = htmlRelaPath;
    }

    public String getJsRelaPath() {
        return jsRelaPath;
    }

    public void setJsRelaPath(String jsRelaPath) {
        this.jsRelaPath = jsRelaPath;
    }

    public String getCustomEntityName() {
        return customEntityName;
    }

    public void setCustomEntityName(String customEntityName) {
        this.customEntityName = customEntityName;
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
