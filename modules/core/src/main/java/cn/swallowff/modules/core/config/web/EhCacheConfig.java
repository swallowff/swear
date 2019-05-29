package cn.swallowff.modules.core.config.web;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
//@EnableCaching
public class EhCacheConfig {

//    @Bean
//    public EhCacheCacheManager ehCacheManager(CacheManager cacheManager) {
//        return new EhCacheCacheManager(cacheManager);
//    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("/config/ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }
}
