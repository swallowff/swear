package cn.swallowff.code.resolver;

import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.entity.TableColumn;
import cn.swallowff.code.exception.GenerationException;
import cn.swallowff.common.lang.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @description
 * @create 2019/7/10
 */
public class MetaDataResolver {
    private String tableName;
    private Connection connection;
    private GeneratorConfig config;

    public MetaDataResolver(GeneratorConfig generatorConfig) {
        this.connection = generatorConfig.getConnection();
        this.tableName = generatorConfig.getTableName();
        this.config = generatorConfig;
    }

    private ResultSet getResultSet() throws SQLException,GenerationException {
        if (null != connection){
            DatabaseMetaData metaData = connection.getMetaData();
            return metaData.getColumns(connection.getCatalog(),"%",tableName,"%");
        }else {
            throw new GenerationException("connection can not be null");
        }
    }

    public List<TableColumn> getTableColumns() throws SQLException,GenerationException{
        ResultSet resultSet = getResultSet();
        List<TableColumn> columnList = new ArrayList<TableColumn>();
        while (resultSet.next()){
            String columnName = resultSet.getString("COLUMN_NAME");
            //id字段略过
            if(StringUtils.equalsAny(columnName,"id","version","create_time","update_time","delflag")) continue;
            TableColumn tableColumn = new TableColumn();
            //获取字段名称
            tableColumn.setColumnName(resultSet.getString("COLUMN_NAME"));
            //获取字段类型
            tableColumn.setColumnType(resultSet.getString("TYPE_NAME"));
            //转换字段名称，如 sys_name 变成 sysName
            tableColumn.setColumnCamelCase(StringUtils.camelCase(columnName));
            //字段在数据库的注释
            tableColumn.setColumnComment(resultSet.getString("REMARKS"));
            columnList.add(tableColumn);
        }
        if (CollectionUtils.isEmpty(columnList)){
            throw new GenerationException("table not found : "+ tableName);
        }
        return columnList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public GeneratorConfig getConfig() {
        return config;
    }

    public void setConfig(GeneratorConfig config) {
        this.config = config;
    }
}
