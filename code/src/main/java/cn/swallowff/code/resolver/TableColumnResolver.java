package cn.swallowff.code.resolver;

import cn.swallowff.code.DataTypeMapping;
import cn.swallowff.code.entity.ClassField;
import cn.swallowff.code.entity.TableColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/10
 */
public class TableColumnResolver {

    public TableColumnResolver() {
    }

    public ClassField resolve(TableColumn tableColumn){
        ClassField classField = new ClassField();
        classField.setFieldName(tableColumn.getColumnCamelCase());
        classField.setRemarks(tableColumn.getColumnComment());
        DataTypeMapping dataTypeMapping = DataTypeMapping.valueOfMysqlType(tableColumn.getColumnType());
        if (null != dataTypeMapping){
            classField.setFieldType(dataTypeMapping.getJavaType());
            classField.setFieldClassName(dataTypeMapping.getJavaType().getSimpleName());
        }else {
            throw new RuntimeException("mysql type is not supported : "+tableColumn.getColumnType());
        }
        return classField;
    }

    public List<ClassField> resolve(List<TableColumn> tableColumns){
        List<ClassField> fields = new ArrayList<>();
        for (TableColumn var1 : tableColumns){
            fields.add(resolve(var1));
        }
        return fields;
    }
}
