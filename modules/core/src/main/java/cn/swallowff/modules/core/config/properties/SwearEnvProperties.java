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
    private boolean websocketOpen;
    private String ffmpegBin;
    private String mediaServer;
    private String serverUrl;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

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

    public boolean isWebsocketOpen() {
        return websocketOpen;
    }

    public void setWebsocketOpen(boolean websocketOpen) {
        this.websocketOpen = websocketOpen;
    }

    public String getFfmpegBin() {
        return ffmpegBin;
    }

    public void setFfmpegBin(String ffmpegBin) {
        this.ffmpegBin = ffmpegBin;
    }

    public String getMediaServer() {
        return mediaServer;
    }

    public void setMediaServer(String mediaServer) {
        this.mediaServer = mediaServer;
    }
}
