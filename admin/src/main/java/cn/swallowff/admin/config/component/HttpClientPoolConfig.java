package cn.swallowff.admin.config.component;

import cn.swallowff.admin.util.PooledHttpClientAdaptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @description
 * @create 2019/6/26
 */
@Configuration
public class HttpClientPoolConfig {

    // httpClient连接池的最大连接数
    private int maxTotal = 200;
    // httpClient连接池按route配置的最大连接数
    private int maxPerRoute = 200;
    // tcp connect的超时时间
    private int connectTimeout = 500;
    // 从连接池获取连接的超时时间
    private int connectRequestTimeout = 500;
    // tcp io的读写超时时间
    private int socketTimeout = 2000;

    @Bean
    @Qualifier("defaultHttpClientPool")
    public PooledHttpClientAdaptor defaultHttpClientPool() {
        return new PooledHttpClientAdaptor(maxTotal, maxPerRoute, connectTimeout, connectRequestTimeout, socketTimeout);
    }

    @Bean
    @Qualifier("wxSSLHttpClientPool")
    public PooledHttpClientAdaptor wxSSLHttpClientPool() {
//        return new PooledHttpClientAdaptor(new File(HttpClientPoolConfig.class.getResource("/pay/").getPath().concat("apiclient_cert.p12")),50,50,200,200,2000);
        return new PooledHttpClientAdaptor(10, 10, connectTimeout, connectRequestTimeout, socketTimeout);
    }
}
