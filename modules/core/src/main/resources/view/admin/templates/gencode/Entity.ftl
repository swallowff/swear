package ${package_name}.entity;
import cn.swallow.platform.core.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.String;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

/**
 * 描述: ${class_name} Entity类
 * @author ${author}
 * @date ${date?string('yyyy-MM-dd HH:mm:ss')}
 */

public class ${class_name} extends BaseEntity<${class_name}> implements Serializable {

    <#if table_columns?exists>
        <#list table_columns as column>
            <#if (column.columnType = 'VARCHAR' || column.columnType = 'TEXT' || column.columnType = 'CHAR')>
    private String ${column.changeColumnName?uncap_first};
            <#elseif column.columnType = 'TIMESTAMP'>
    private Timestamp ${column.changeColumnName?uncap_first};
            <#elseif column.columnType = 'DATE'>
            <#assign jsonFormatFlag=true>
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date ${column.changeColumnName?uncap_first};
            <#elseif (column.columnType = 'DATETIME')>
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ${column.changeColumnName?uncap_first};
            <#elseif column.columnType = 'TINYINT' || column.columnType = 'INT'>
    private Integer ${column.changeColumnName?uncap_first};
            <#else>
            </#if>
        </#list>
    </#if>

<#if table_columns?exists>
    <#list table_columns as column>
        <#if (column.columnType = 'VARCHAR' || column.columnType = 'TEXT' || column.columnType = 'CHAR')>
    public String get${column.changeColumnName}() {
        return this.${column.changeColumnName?uncap_first};
    }

    public void set${column.changeColumnName}(String ${column.changeColumnName?uncap_first}) {
        this.${column.changeColumnName?uncap_first} = ${column.changeColumnName?uncap_first};
    }

        </#if>
        <#if column.columnType = 'TIMESTAMP' >
    public Timestamp get${column.changeColumnName}() {
        return this.${column.changeColumnName?uncap_first};
    }

    public void set${column.changeColumnName}(Timestamp ${column.changeColumnName?uncap_first}) {
        this.${column.changeColumnName?uncap_first} = ${column.changeColumnName?uncap_first};
    }

        </#if>
        <#if column.columnType = 'DATE' >
    public Date get${column.changeColumnName}() {
        return this.${column.changeColumnName?uncap_first};
    }

    public void set${column.changeColumnName}(Date ${column.changeColumnName?uncap_first}) {
        this.${column.changeColumnName?uncap_first} = ${column.changeColumnName?uncap_first};
    }

        </#if>
        <#if column.columnType = 'DATETIME' >
    public Date get${column.changeColumnName}() {
        return this.${column.changeColumnName?uncap_first};
    }

    public void set${column.changeColumnName}(Date ${column.changeColumnName?uncap_first}) {
        this.${column.changeColumnName?uncap_first} = ${column.changeColumnName?uncap_first};
    }

        </#if>
        <#if column.columnType = 'TINYINT' || column.columnType = 'INT' >
    public Integer get${column.changeColumnName}() {
        return this.${column.changeColumnName?uncap_first};
    }

    public void set${column.changeColumnName}(Integer ${column.changeColumnName?uncap_first}) {
        this.${column.changeColumnName?uncap_first} = ${column.changeColumnName?uncap_first};
    }

        </#if>
    </#list>
</#if>
}

