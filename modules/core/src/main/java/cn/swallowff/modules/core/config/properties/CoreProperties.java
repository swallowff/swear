package cn.swallowff.modules.core.config.properties;

import cn.swallowff.common.io.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Optional;

@Component
@PropertySource(value = "classpath:config/swear-core.properties")
public class CoreProperties {
    private PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();

    private String serverUrl = propertiesUtils.getProperty("swear-path-server-url");
    private String ctx = propertiesUtils.getProperty("swear-path-ctx");
    private String adminPath = propertiesUtils.getProperty("swear-path-admin");
    private String staticPath = propertiesUtils.getProperty("swear-path-static");
    private String fileUploadPath = propertiesUtils.getProperty("swear-path-file-upload");
    private boolean kaptchaOpen = propertiesUtils.getBooleanProperty("swear-core-kaptcha-open");
    private boolean swaggerOpen = propertiesUtils.getBooleanProperty("swear-core-swagger-open");


    /**
     * session 失效时间（默认为30分钟 单位：秒）
     */
    private Integer sessionInvalidateTime = 30 * 60;

    /**
     * session 验证失效时间（默认为15分钟 单位：秒）
     */
    private Integer sessionValidationInterval = 15 * 60;

    public String getFileUploadPath() {
        if (fileUploadPath.endsWith(File.separator)) {
            return fileUploadPath;
        } else {
            return fileUploadPath.concat(File.separator);
        }
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public String getAdminPath() {
        return adminPath;
    }

    public void setAdminPath(String adminPath) {
        this.adminPath = adminPath;
    }

    public Integer getSessionInvalidateTime() {
        return sessionInvalidateTime;
    }

    public void setSessionInvalidateTime(Integer sessionInvalidateTime) {
        this.sessionInvalidateTime = sessionInvalidateTime;
    }

    public Integer getSessionValidationInterval() {
        return sessionValidationInterval;
    }

    public void setSessionValidationInterval(Integer sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }

    public boolean isKaptchaOpen() {
        return kaptchaOpen;
    }

    public void setKaptchaOpen(boolean kaptchaOpen) {
        this.kaptchaOpen = kaptchaOpen;
    }

    public boolean isSwaggerOpen() {
        return swaggerOpen;
    }

    public void setSwaggerOpen(boolean swaggerOpen) {
        this.swaggerOpen = swaggerOpen;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getCtx() {
        return ctx;
    }

    public void setCtx(String ctx) {
        this.ctx = ctx;
    }

    public String getStaticPath() {
        return staticPath;
    }

    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath;
    }

}
