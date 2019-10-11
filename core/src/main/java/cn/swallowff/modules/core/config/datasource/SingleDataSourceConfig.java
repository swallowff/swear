package cn.swallowff.modules.core.config.datasource;

import cn.swallowff.modules.core.config.properties.DruidProperties;
import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author shenyu
 * @create 2019/4/9
 */
@Configuration
@EnableTransactionManagement
@ConditionalOnProperty(prefix = "swear.muti-datasource", name = "open", havingValue = "false")
@MapperScan(basePackages = {"cn.swallowff.**.dao"})
public class SingleDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-default")
    public DruidProperties druidProperties() {
        return new DruidProperties();
    }

    @Bean
    public DruidDataSource dataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

}
