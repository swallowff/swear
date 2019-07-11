package cn.swallowff.code;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shenyu
 * @create 2019/7/10
 */
public enum DataTypeMapping {
    INT("INT",Integer.class),
    TINY_INT("TINYINT",Integer.class),
    BIG_INT("BIGINT",Long.class),
    CHAR("CHAR",String.class),
    VARCHAR("VARCHAR",String.class),
    TEXT("TEXT",String.class),
    DECIMAL("DECIMAL",BigDecimal.class),
    DOUBLE("DOUBLE",Double.class),
    FLOAT("FLOAT",Float.class),
    DATETIME("DATETIME",Date.class),
    BIT("BIT",Boolean.class)
    ;
    private String mysqlType;

    private Class javaType;

    DataTypeMapping(String mysqlType, Class javaType) {
        this.mysqlType = mysqlType;
        this.javaType = javaType;
    }

    public static DataTypeMapping valueOfMysqlType(String mysqlType){
        DataTypeMapping[] values = DataTypeMapping.values();
        for (DataTypeMapping var1 : values){
            if (var1.mysqlType.equals(mysqlType)){
                return var1;
            }
        }
        return null;
    }

    public String getMysqlType() {
        return mysqlType;
    }

    public Class getJavaType() {
        return javaType;
    }
}
