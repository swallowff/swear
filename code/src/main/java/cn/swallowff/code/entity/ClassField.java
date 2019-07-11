package cn.swallowff.code.entity;

import java.util.List;

/**
 * @author Administrator
 * @description
 * @create 2019/7/10
 */
public class ClassField {
    private List<String> annotations; //注解
    private String fieldName;       //属性名
    private Class fieldType;       //属性类型
    private String fieldClassName;  //属性类型名
    private String remarks;      //属性注释
    private String formType;       //TODO 表单类型  input-text  radio  select   switch

    public String getFieldClassName() {
        return fieldClassName;
    }

    public void setFieldClassName(String fieldClassName) {
        this.fieldClassName = fieldClassName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Class getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class fieldType) {
        this.fieldType = fieldType;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }
}
