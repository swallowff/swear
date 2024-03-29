package cn.swallowff.admin.config.web;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @description
 * @create 2019/7/22
 */
@Configuration
public class ESConfig {

    @Bean
    public RestHighLevelClient client() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        return client;
    }

}
