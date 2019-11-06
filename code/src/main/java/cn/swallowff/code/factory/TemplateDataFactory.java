package cn.swallowff.code.factory;

import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.entity.ClassEntity;
import cn.swallowff.code.entity.ClassField;
import cn.swallowff.code.entity.TableColumn;
import cn.swallowff.code.entity.TemplateData;
import cn.swallowff.code.exception.GenerationException;
import cn.swallowff.code.resolver.ImportClassResolver;
import cn.swallowff.code.resolver.MetaDataResolver;
import cn.swallowff.code.resolver.TableColumnResolver;
import cn.swallowff.code.util.PathUtils;
import cn.swallowff.code.util.StringUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

    public TemplateData createTemplateData() throws GenerationException{
        try {
            tableColumns = metaDataResolver.getTableColumns();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        GeneratorConfig config = metaDataResolver.getConfig();
        TemplateData templateData = new TemplateData();
        templateData.setAuthor("swallowff");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        templateData.setDate(dateFormat.format(new Date()));
        templateData.setTableName(config.getTableName());
        templateData.setBasePackage(PathUtils.pathToPackage(config.getJavaRelaPath()));
        String tablePrefix = config.getTablePrefix();
        String className;
        String customClassName = config.getCustomEntityName();
        if (customClassName != null && !"".equals(customClassName)){
            className = StringUtils.cap(customClassName);
            if (null == tablePrefix){

            }else if (tablePrefix.endsWith("_")){
                templateData.setTablePrefix(tablePrefix.replace("_",""));
            }else {
                templateData.setTablePrefix(tablePrefix);
            }
        }else {
            if (null == tablePrefix){
                className = StringUtils.capCamelCase(tableName);
            } else if (tablePrefix.endsWith("_")){
                templateData.setTablePrefix(tablePrefix.replace("_",""));
                className = StringUtils.capCamelCase(tableName.replace(tablePrefix,""));
            }else {
                templateData.setTablePrefix(tablePrefix);
                className = StringUtils.capCamelCase(tableName.replace(tablePrefix.concat("_"),""));
            }
        }

        templateData.setClassName(className);
        templateData.setUncapClassName(StringUtils.uncap(className));
        templateData.setTitle(config.getTitle());
        String htmlRelativePath = config.getHtmlRelaPath();
        templateData.setHtmlRelativePath(htmlRelativePath);
        String jsRelativePath = config.getJsRelaPath();
        templateData.setJsRelativePath(jsRelativePath);
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
