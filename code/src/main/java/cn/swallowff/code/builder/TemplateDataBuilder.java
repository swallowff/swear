package cn.swallowff.code.builder;

import cn.swallowff.code.entity.ClassEntity;
import cn.swallowff.code.entity.TableColumn;
import cn.swallowff.code.entity.TemplateData;
import cn.swallowff.common.lang.DateUtils;
import cn.swallowff.common.lang.StringUtils;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @description
 * @create 2019/7/10
 */
public class TemplateDataBuilder {
    private String basePackage;
    private String javaFileBasePath;
    private String htmlFileBasePath;
    private String jsFileBasePath;
    private String tableName;
    private List<TableColumn> tableColumns;

    public TemplateDataBuilder(String tableName,String basePackage, String javaFileBasePath, String htmlFileBasePath, String jsFileBasePath, List<TableColumn> tableColumns) {
        this.basePackage = basePackage;
        this.javaFileBasePath = javaFileBasePath;
        this.htmlFileBasePath = htmlFileBasePath;
        this.jsFileBasePath = jsFileBasePath;
        this.tableColumns = tableColumns;
        this.tableName = tableName;
    }

    public TemplateData build(){
        TemplateData templateData = new TemplateData();
        templateData.setAuthor("swallowff");
        templateData.setDate(DateUtils.formatDate(new Date(),"yyyy年MM月dd日"));
        templateData.setBasePackage(basePackage);
        templateData.setClassName(StringUtils.capCamelCase(tableName));

        return null;
    }

    private ClassEntity convertTableColumnToClassEntity(List<TableColumn> tableColumns){
        ClassEntity classEntity = new ClassEntity();
//        String classPackage = new File("").getAbsolutePath().concat()
//        classEntity.setPackageName();
        for (TableColumn var1 : tableColumns){


        }
        return null;
    }


}
