package cn.swallowff.code.gen;

import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.modules.core.util.SpringContextHolder;
import cn.swallowff.web.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GeneratorImplTest {

    @Test
    public void genEntity() throws Exception{
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.setTableName("test_gen");
//        generatorConfig.setJavaLocation("cn.swallowff.modules.core.modules.system");
//        generatorConfig.setMapperLocation("mapper.system");
//        generatorConfig.setHtmlLocation("WEB-INF.pages.admin.system");
//        generatorConfig.setJsLocation("static.module.admin.system");
        DataSource dataSource = SpringContextHolder.getBean(DataSource.class);
        generatorConfig.setConnection(dataSource.getConnection());
        generatorConfig.setTemplatePath("/WEB-INF/view/pages/template/gencode/");
        IGenerator generator = new GeneratorImpl(generatorConfig);
        generator.execute();

    }
}