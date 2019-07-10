package cn.swallowff.code.resolver;

import cn.swallowff.code.entity.TableColumn;
import cn.swallowff.common.lang.StringUtils;

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

    public MetaDataResolver(Connection connection,String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    public ResultSet getMetaData() throws SQLException {
        if (null != connection){
            DatabaseMetaData metaData = connection.getMetaData();
            return metaData.getColumns(null,"%",tableName,"%");
        }else return null;
    }

    public List<TableColumn> resolveResultSet(ResultSet resultSet) throws SQLException{
        List<TableColumn> columnList = new ArrayList<TableColumn>();
        while (resultSet.next()){
            String columnName = resultSet.getString("COLUMN_NAME");
            //id字段略过
            if(columnName.equals("id")) continue;
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
        return columnList;
    }






}
