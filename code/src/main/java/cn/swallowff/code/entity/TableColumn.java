package cn.swallowff.code.entity;

/**
 * @author Administrator
 * @description
 * @create 2019/7/10
 */
public class TableColumn {
    private String columnName;   //数据表字段名称
    private String columnType;      //表字段类型
    private String columnComment;    //字段注释
    private String columnCamelCase;  //字段名驼峰

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnCamelCase() {
        return columnCamelCase;
    }

    public void setColumnCamelCase(String columnCamelCase) {
        this.columnCamelCase = columnCamelCase;
    }
}
