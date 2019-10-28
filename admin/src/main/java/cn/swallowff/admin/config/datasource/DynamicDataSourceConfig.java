package cn.swallowff.admin.config.datasource;

import cn.swallowff.admin.config.properties.DruidProperties;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid读写分离数据源配置
 */
@Configuration
@EnableTransactionManagement
@ConditionalOnProperty(prefix = "swear.muti-datasource", name = "open", havingValue = "true")
@MapperScan(basePackages = {"cn.swallowff.**.dao"})
public class DynamicDataSourceConfig {
    @Value("${spring.datasource-master.type}")
    private Class<? extends DataSource> dataSourceType;
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    //    master数据源配置
    @Bean(name = "masterDruidProperties")
    @ConfigurationProperties(prefix = "spring.datasource-master")
    public DruidProperties druidProperties() {
        return new DruidProperties();
    }

    //slave数据源配置
    @Bean(name = "slaveDruidProperties")
    @ConfigurationProperties(prefix = "spring.datasource-slave")
    public DruidProperties slaveDruidProperties() {
        return new DruidProperties();
    }

    public DruidDataSource writedataSource(@Qualifier("masterDruidProperties") DruidProperties masterProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        masterProperties.config(dataSource);
        return dataSource;
    }

    public DruidDataSource readDataSource(@Qualifier("slaveDruidProperties") DruidProperties slaveProperties) {
        DruidDataSource readDataSource = new DruidDataSource();
        slaveProperties.config(readDataSource);
        return readDataSource;
    }

    /**
     * dataSource路由,配合Spring-aop使不同的mapper方法使用不同的数据源
     *
     * @return
     */
    @Bean
    public MyRoutingDataSource routingDataSource() {
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.READ.getType(), readDataSource(slaveDruidProperties()));
        dataSourceMap.put(DataSourceType.WRITE.getType(), writedataSource(druidProperties()));
        myRoutingDataSource.setTargetDataSources(dataSourceMap);
//        DataSource writedataSource = DataSourceBuilder.create().type(dataSourceType).build();
        return myRoutingDataSource;
    }

    /**
     * 多数据源情况下需要手动配置sqlSessionFactory
     *
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(routingDataSource());
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setTypeAliasesPackage(typeAliasesPackage);
        bean.setMapperLocations(resolver.getResources(mapperLocations));
//        bean.setConfigLocation(resolver.getResource(confi));
        return bean.getObject();
    }

    /**
     * mybatis-plus分页插件
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }

    /**
     * 数据范围mybatis插件
     */
//    @Bean
//    public DataScopeInterceptor dataScopeInterceptor() {
//        return new DataScopeInterceptor();
//    }

    /**
     * 乐观锁mybatis插件
     */
//    @Bean
//    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
//        return new OptimisticLockerInterceptor();
//    }

}

