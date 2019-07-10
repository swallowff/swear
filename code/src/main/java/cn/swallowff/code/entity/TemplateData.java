package cn.swallowff.code.entity;

import java.util.List;

/**
 * @author Administrator
 * @description
 * @create 2019/7/10
 */
public class TemplateData {
    private String author;  //作者
    private String className;   //类名
    private String date;        //日期
    private String basePackage; //基础包路径
    private String javaFileBasePath;  //.java文件基础路径
    private String htmlFileBasePath;  //.html文件基础路径
    private String jsFileBasePath;    //.js文件基础路径
    private List<ClassEntity> items;

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

    public String getJavaFileBasePath() {
        return javaFileBasePath;
    }

    public void setJavaFileBasePath(String javaFileBasePath) {
        this.javaFileBasePath = javaFileBasePath;
    }

    public String getHtmlFileBasePath() {
        return htmlFileBasePath;
    }

    public void setHtmlFileBasePath(String htmlFileBasePath) {
        this.htmlFileBasePath = htmlFileBasePath;
    }

    public String getJsFileBasePath() {
        return jsFileBasePath;
    }

    public void setJsFileBasePath(String jsFileBasePath) {
        this.jsFileBasePath = jsFileBasePath;
    }

    public List<ClassEntity> getItems() {
        return items;
    }

    public void setItems(List<ClassEntity> items) {
        this.items = items;
    }
}
