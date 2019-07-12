package cn.swallowff.code.entity;

import cn.swallowff.code.GenFileType;

/**
 * @author Administrator
 * @description
 * @create 2019/7/12
 */
public class GeneratorFile {
    private String name;
    private String nameSuffix;
    private String fileSuffix;
    private String moduleName;
    private String templateName;
    private GenFileType fileType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public GenFileType getFileType() {
        return fileType;
    }

    public void setFileType(GenFileType fileType) {
        this.fileType = fileType;
    }
}
