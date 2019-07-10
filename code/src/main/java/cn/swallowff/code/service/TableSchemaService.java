package cn.swallowff.code.service;

import cn.swallowff.code.util.SpringContextHolder2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 查询数据库所有表的信息
 * @author shenyu
 * @create 2019/3/16
 */
@Service
public class TableSchemaService {
    @Value("${spring.datasource-default.db-name}")
    private String dbName;

    public TableSchemaService(){}

    public List<Map<String,Object>> getAllTables(){
        String sql = "select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = '" + this.dbName + "'";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringContextHolder2.getBean(DataSource.class),true);
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }
}
