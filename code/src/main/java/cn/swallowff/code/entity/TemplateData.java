package cn.swallowff.code.entity;

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
}
