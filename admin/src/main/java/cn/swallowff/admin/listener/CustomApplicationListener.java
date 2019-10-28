package cn.swallowff.admin.listener;

import cn.swallowff.admin.components.chat.ChatUserService;
import cn.swallowff.admin.config.properties.SwearEnvProperties;
import cn.swallowff.admin.util.SpringContextHolder;
import cn.swallowff.modules.chat.core.SwearMsgHandler;
import cn.swallowff.modules.chat.core.SwearSocketServerStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
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
        if (swearEnvProperties.isWebsocketOpen()){
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
}
