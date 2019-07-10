package cn.swallowff.code.service;

import cn.swallowff.code.entity.ClassEntity;
import cn.swallowff.code.entity.TableColumn;
import cn.swallowff.code.resolver.MetaDataResolver;
import cn.swallowff.code.util.SpringContextHolder2;
import cn.swallowff.common.json.JacksonUtil;
import cn.swallowff.web.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TableSchemaServiceTest {
    @Autowired
    private TableSchemaService tableSchemaService;

    @Test
    public void getAllTables() {
        List<Map<String,Object>> tables = tableSchemaService.getAllTables();
        System.out.println(JacksonUtil.toJson(tables));
    }

    @Test
    public void testDataMetaResolver() throws Exception{
        DataSource dataSource = SpringContextHolder2.getBean(DataSource.class);
        MetaDataResolver metaDataResolver = new MetaDataResolver(dataSource.getConnection(),"test_gen");
        ResultSet resultSet = metaDataResolver.getMetaData();
        List<TableColumn> list = metaDataResolver.resolveResultSet(resultSet);
        System.out.println(JacksonUtil.toJson(list));
    }

}