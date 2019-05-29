package cn.swallowff.modules.core.config.web;

import cn.swallowff.modules.core.config.properties.CoreProperties;
import cn.swallowff.modules.core.filter.SystemUserFilter;
import cn.swallowff.modules.core.shiro.ShiroDBRealm;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author shenyu
 * @create 19-5-30
 */
@Configuration
public class ShiroConfig2 {

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(EhCacheManager ehCacheManager,DefaultWebSessionManager sessionManager){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(shiroDBRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(ehCacheManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager em = new EhCacheManager();
        //将ehcacheManager转换成shiro包装后的ehcacheManager对象
        em.setCacheManager(new CacheManager());
        em.setCacheManagerConfigFile("classpath:config/ehcache.xml");
        return em;
    }
    /**
     * shiro session的管理
     */
    @Bean
    @ConditionalOnProperty(prefix = "swear", name = "spring-session-open", havingValue = "false")
    public DefaultWebSessionManager sessionManager(CoreProperties coreProperties) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationInterval(coreProperties.getSessionValidationInterval() * 1000);
        sessionManager.setGlobalSessionTimeout(coreProperties.getSessionInvalidateTime() * 1000);
        //设置sessionDao对session查询，在查询在线用户service中用到了
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("shiroCookie");
        cookie.setHttpOnly(true);
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }

    @Bean
    public SessionDAO sessionDAO(){
        return new MemorySessionDAO();
    }

    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberme");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        return cookie;
    }

    @Bean
    public ShiroDBRealm shiroDBRealm(){
        return new ShiroDBRealm();
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        /**
         * 默认的登陆访问url
         */
        shiroFilter.setLoginUrl("/a/login");
        /**
         * 登陆成功后跳转的url
         */
        shiroFilter.setSuccessUrl("/a");
        /**
         * 没有权限跳转的url
         */
        shiroFilter.setUnauthorizedUrl("/global/error");

        /**
         * 覆盖默认的user拦截器(默认拦截器解决不了ajax请求 session超时的问题,若有更好的办法请及时反馈作者)
         */
        HashMap<String, Filter> myFilters = new HashMap<>();
        myFilters.put("user", new SystemUserFilter());
        shiroFilter.setFilters(myFilters);

        /**
         * 配置shiro拦截器链
         *
         * anon  不需要认证
         * authc 需要认证
         * user  验证通过或RememberMe登录的都可以
         *
         * 当应用开启了rememberMe时,用户下次访问时可以是一个user,但不会是authc,因为authc是需要重新认证的
         *
         * 顺序从上到下,优先级依次降低
         *
         * openApi开头的接口，走rest api鉴权，不走shiro鉴权
         *
         */
        Map<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("/static/**", "anon");
        hashMap.put("/a/test/**","anon");
//        hashMap.put("/blog","blog");
        hashMap.put("/openApi/**", "anon");
        hashMap.put("/a/login", "anon");
        hashMap.put("/a/swaggerApi/**","anon");
        //放行swagger2
        hashMap.put("/swagger-ui.html","anon");
        hashMap.put("/swagger-resources","anon");
        hashMap.put("/v2/api-docs","anon");
        hashMap.put("/webjars/springfox-swagger-ui/**","anon");

        hashMap.put("/global/**", "anon");
        hashMap.put("/a/kaptcha", "anon");
        hashMap.put("/a/**", "user");
        shiroFilter.setFilterChainDefinitionMap(hashMap);
        return shiroFilter;
    }



}
