package cn.swallowff.modules.core.listener;

import cn.swallowff.modules.chat.core.SwearMsgHandler;
import cn.swallowff.modules.chat.core.SwearSocketServerStarter;
import cn.swallowff.modules.core.chat.ChatUserService;
import cn.swallowff.modules.core.config.properties.SwearEnvProperties;
import cn.swallowff.modules.core.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @description
 * @create 2019/7/20
 */
@Component
public class CustomApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private SwearEnvProperties swearEnvProperties;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //do something when spring application started
        //TODO 启动nio聊天服务
        SwearMsgHandler swearMsgHandler = SwearMsgHandler.me;
        swearMsgHandler.registerUserService(SpringContextHolder.getBean(ChatUserService.class));
        try {
            SwearSocketServerStarter serverStarter = new SwearSocketServerStarter(swearEnvProperties.getWebsocketPort(),swearMsgHandler);
            serverStarter.doStart();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
