package cn.swallowff.code.factory;

import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.entity.ClassEntity;
import cn.swallowff.code.entity.ClassField;
import cn.swallowff.code.entity.TableColumn;
import cn.swallowff.code.entity.TemplateData;
import cn.swallowff.code.resolver.ImportClassResolver;
import cn.swallowff.code.resolver.MetaDataResolver;
import cn.swallowff.code.resolver.TableColumnResolver;
import cn.swallowff.code.util.PathUtils;
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
        GeneratorConfig config = metaDataResolver.getConfig();
        TemplateData templateData = new TemplateData();
        templateData.setAuthor("swallowff");
        templateData.setDate(DateUtils.formatDate(new Date(),"yyyy/MM/dd"));
        templateData.setTableName(config.getTableName());
        templateData.setBasePackage(config.getJavaLocation());
        String className = StringUtils.capCamelCase(tableName.replace(config.getTablePrefix().concat("_"),""));
        templateData.setClassName(className);
        templateData.setUncapClassName(StringUtils.uncap(className));
        templateData.setTitle(config.getTitle());
        templateData.setTablePrefix(config.getTablePrefix());
        String htmlLocation = PathUtils.packageToRelativePath(config.getHtmlLocation()).replaceFirst("/WEB-INF/view","");
        templateData.setHtmlRelativePath(htmlLocation);
        String jsLocation = PathUtils.packageToRelativePath(config.getJsLocation()).replaceFirst("/WEB-INF/static","");
//        jsLocation = org.apache.commons.lang3.StringUtils.substringBeforeLast(jsLocation,".");
        templateData.setJsRelativePath(jsLocation);
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
