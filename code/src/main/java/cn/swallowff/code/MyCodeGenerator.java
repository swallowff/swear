package cn.swallowff.code;

import cn.swallowff.code.config.GeneratorConfig;
import cn.swallowff.code.factory.GeneratorFileFactory;
import cn.swallowff.code.gen.GeneratorImpl;
import cn.swallowff.code.gen.IGenerator;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author shenyu
 * @create 2019/11/6
 */
public class MyCodeGenerator {

        public static void main(String[] args) throws Exception{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/swallow_master?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai";

            Connection connection = DriverManager.getConnection(url,"root","yanni623..");

            GeneratorConfig generatorConfig = new GeneratorConfig();
            generatorConfig.setConnection(connection);
            generatorConfig.getGenFileSets();
            generatorConfig.setTableName("sys_user");
            generatorConfig.setCustomEntityName("SwearUser");
            generatorConfig.setTablePrefix("sys_");
            generatorConfig.setForceCover(true);
            generatorConfig.setJavaRelaPath("cn/swallowff/user");
            generatorConfig.setMapperRelaPath("");
            generatorConfig.setHtmlRelaPath("pages");
            generatorConfig.setTemplatePath("/templates/base");
            generatorConfig.setTitle("用户");
            generatorConfig.setModuleLocation("output");
            generatorConfig.setGenFileSets(GeneratorFileFactory.createDefaultAll());

            IGenerator generator = new GeneratorImpl(generatorConfig);
            generator.execute();

        }

}
