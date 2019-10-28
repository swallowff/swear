package cn.swallowff.admin.config.web;

import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
//@EnableCaching
public class EhCacheConfig {

//    @Bean
//    public CacheManager cacheManager(){
//        return CacheManager.getInstance();
//    }

//    @Bean
//    public EhCacheCacheManager ehCacheManager() {
//        return new EhCacheCacheManager();
//    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("/ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }
}
