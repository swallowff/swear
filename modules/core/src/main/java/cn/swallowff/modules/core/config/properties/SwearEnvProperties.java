package cn.swallowff.modules.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenyu
 * @create 2019/7/23
 */
@Configuration
@ConfigurationProperties(prefix = "swear")
public class SwearEnvProperties {
    private String websocketUrl;
    private Integer websocketPort;

    public String getWebsocketUrl() {
        return websocketUrl;
    }

    public void setWebsocketUrl(String websocketUrl) {
        this.websocketUrl = websocketUrl;
    }

    public Integer getWebsocketPort() {
        return websocketPort;
    }

    public void setWebsocketPort(Integer websocketPort) {
        this.websocketPort = websocketPort;
    }
}
