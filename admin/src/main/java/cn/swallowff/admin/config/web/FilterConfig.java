package cn.swallowff.admin.config.web;

import cn.swallowff.admin.cmomon.filter.HttpServletRequestFilter;
import cn.swallowff.admin.cmomon.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenyu
 * @create 2019/3/13
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(1);

        registration.setFilter(new HttpServletRequestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("replaceHttpServlet");
        registration.setOrder(2);
        return registration;

    }
}
