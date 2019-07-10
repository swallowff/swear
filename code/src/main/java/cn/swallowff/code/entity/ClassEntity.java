package cn.swallowff.code.entity;

import java.util.List;

/**
 * @author Administrator
 * @description
 * @create 2019/7/10
 */
public class ClassEntity {
    private String packageName;   //类的完整包名
    private List<String> importClasses;   //导入的类
    private List<ClassField> fields;       //属性列表

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getImportClasses() {
        return importClasses;
    }

    public void setImportClasses(List<String> importClasses) {
        this.importClasses = importClasses;
    }

    public List<ClassField> getFields() {
        return fields;
    }

    public void setFields(List<ClassField> fields) {
        this.fields = fields;
    }
}
