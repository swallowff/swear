package cn.swallowff.code.datasource;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description
 * @create 2019/7/10
 */
public interface TableSchema {
    List<Map<String,Object>> getAllTables();
}
