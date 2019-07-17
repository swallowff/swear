package cn.swallowff.modules.core.config.properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载自定义的properties文件
 *
 * @author shenyu
 * @create 2019/3/15
 */
@Configuration
@PropertySource({"classpath:/config/swear-core.properties"})
public class PropertiesAutoConfiguration {
    public PropertiesAutoConfiguration() {
    }


}
