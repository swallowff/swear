package cn.swallowff.admin.modules.system.service;

import cn.swallowff.admin.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author shenyu
 * @create 2019/7/11
 */
@Service
public class CodeService {
    @Value("${spring.datasource-default.db-name}")
    private String dbName;

    public List<Map<String, Object>> getAllTables() {
        String sql = "select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = '" + this.dbName + "'";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringContextHolder.getBean(DataSource.class), true);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }

}
