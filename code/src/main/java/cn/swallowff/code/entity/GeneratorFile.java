package cn.swallowff.code.entity;

import cn.swallowff.code.GenFileType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Objects;

/**
 * @author Administrator
 * @description
 * @create 2019/7/12
 */
public class GeneratorFile {
    private String name;     //名称
    private String nameSuffix;   //文件名称后缀  Dao  Controller  Service   -list   -add   -edit   Mapper
    private String fileSuffix;   //文件后缀     .java   .html    .js
    private String moduleName;   //模块名称     entity   dao   service
    private String templateName;  //模板名称    entity.btl   dao.btl
    private GenFileType fileType; //生成类型   目前支持四种: java html js mapper

    public GeneratorFile() {
    }

    public GeneratorFile(String name, String nameSuffix, String fileSuffix, String moduleName, String templateName, GenFileType fileType) {
        this.name = name;
        this.nameSuffix = nameSuffix;
        this.fileSuffix = fileSuffix;
        this.moduleName = moduleName;
        this.templateName = templateName;
        this.fileType = fileType;
    }

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        GeneratorFile that = (GeneratorFile) object;

        return new EqualsBuilder()
                .append(name, that.name)
                .append(nameSuffix, that.nameSuffix)
                .append(fileSuffix, that.fileSuffix)
                .append(moduleName, that.moduleName)
                .append(templateName, that.templateName)
                .append(fileType, that.fileType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(nameSuffix)
                .append(fileSuffix)
                .append(moduleName)
                .append(templateName)
                .append(fileType)
                .toHashCode();
    }
}
