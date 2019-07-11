package cn.swallowff.code.entity;

/**
 * @author Administrator
 * @description
 * @create 2019/7/10
 */
public class TemplateData {
    private String author;  //作者
    private String className;   //类名(大写)
    private String uncapClassName;   //类名(首字母小写)
    private String date;        //日期
    private String basePackage; //基础包路径
    private String tableName;   //表名
    private String tablePrefix; //表名前缀
    private String title;  //实体类名称
    private String htmlRelativePath;  //html相对webapps的路径
    private String jsRelativePath;  //js相对webapps的路径
    private ClassEntity item;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public ClassEntity getItem() {
        return item;
    }

    public void setItem(ClassEntity item) {
        this.item = item;
    }

    public String getHtmlRelativePath() {
        return htmlRelativePath;
    }

    public void setHtmlRelativePath(String htmlRelativePath) {
        this.htmlRelativePath = htmlRelativePath;
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

    public String getUncapClassName() {
        return uncapClassName;
    }

    public void setUncapClassName(String uncapClassName) {
        this.uncapClassName = uncapClassName;
    }

    public String getJsRelativePath() {
        return jsRelativePath;
    }

    public void setJsRelativePath(String jsRelativePath) {
        this.jsRelativePath = jsRelativePath;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
