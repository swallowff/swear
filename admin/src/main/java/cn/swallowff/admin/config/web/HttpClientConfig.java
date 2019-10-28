package cn.swallowff.admin.config.web;

import cn.swallowff.admin.util.PooledHttpClientAdaptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author shenyu
 * @create 2019/5/9
 */
@Component
@ConfigurationProperties(prefix = "http.client.pool")
public class HttpClientConfig {
    // 连接池的最大连接数
    private int maxTotal = 200;
    // 连接池按route配置的最大连接数
    private int maxPerRoute = 200;
    // tcp connect的超时时间
    private int connectTimeout = 500;
    // 从连接池获取连接的超时时间
    private int connectRequestTimeout = 500;
    // tcp io的读写超时时间
    private int socketTimeout = 2000;

    @Bean
    public PooledHttpClientAdaptor pooledHttpClientAdaptor() {
        return new PooledHttpClientAdaptor(maxTotal, maxPerRoute, connectTimeout, connectRequestTimeout, socketTimeout);
    }
}
