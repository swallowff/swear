package cn.swallowff.modules.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContext启动时会自动注入进来，可用于获取由spring维护的对象
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        assertApplicationContext();
        return context;
    }

    public static Object getBean(String beanName){
        assertApplicationContext();
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertApplicationContext();
        return context.getBean(requiredType);
    }


    public static void assertApplicationContext(){
        if (null == context){
            throw new RuntimeException("application context inject fail");
        }
    }
}
