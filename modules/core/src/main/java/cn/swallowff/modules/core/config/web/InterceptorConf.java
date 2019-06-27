package cn.swallowff.modules.core.config.web;

import cn.swallowff.modules.core.config.properties.CoreProperties;
import cn.swallowff.modules.core.filter.WebApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author shenyu
 * @create 19-6-28
 */
@Configuration
public class InterceptorConf extends WebMvcConfigurationSupport {
    @Autowired
    private CoreProperties coreProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new WebApiInterceptor()).addPathPatterns("/a/**");
        super.addInterceptors(registry);
    }

    /**
     * 表示这些配置的是静态文件所处路径， 不用拦截
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
        if (coreProperties.isSwaggerOpen()) {
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
            super.addResourceHandlers(registry);
        }
    }

}
