package cn.swallowff.code.factory;

import cn.swallowff.code.entity.ClassEntity;
import cn.swallowff.code.entity.ClassField;
import cn.swallowff.code.entity.TableColumn;
import cn.swallowff.code.entity.TemplateData;
import cn.swallowff.code.resolver.ImportClassResolver;
import cn.swallowff.code.resolver.MetaDataResolver;
import cn.swallowff.code.resolver.TableColumnResolver;
import cn.swallowff.common.lang.DateUtils;
import cn.swallowff.common.lang.StringUtils;
import javafx.util.Builder;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @description
 * @create 2019/7/10
 */
public class TemplateDataFactory {
//    private String basePackage;
    private String tableName;
    private List<TableColumn> tableColumns;
    private MetaDataResolver metaDataResolver;

    public TemplateDataFactory(MetaDataResolver metaDataResolver) {
        this.tableName = metaDataResolver.getTableName();
        this.metaDataResolver = metaDataResolver;
    }

    public TemplateData createTemplateData(){
        try {
            tableColumns = metaDataResolver.getTableColumns();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        TemplateData templateData = new TemplateData();
        templateData.setAuthor("swallowff");
        templateData.setDate(DateUtils.formatDate(new Date(),"yyyy年MM月dd日"));
        templateData.setBasePackage(metaDataResolver.getConfig().getJavaLocation());
        templateData.setClassName(StringUtils.capCamelCase(tableName));
        templateData.setItem(wrapClassEntity(tableColumns));
        return templateData;
    }

    private ClassEntity wrapClassEntity(List<TableColumn> tableColumns){
        ClassEntity classEntity = new ClassEntity();
        TableColumnResolver tableColumnResolver = new TableColumnResolver();
        ImportClassResolver importClassResolver = new ImportClassResolver();
        List<ClassField> fieldList = tableColumnResolver.resolve(tableColumns);
        classEntity.setFields(fieldList);
        classEntity.setImportClasses(importClassResolver.resolve(fieldList));
        return classEntity;
    }


}
