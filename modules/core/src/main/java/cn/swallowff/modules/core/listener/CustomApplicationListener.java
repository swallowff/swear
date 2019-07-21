package cn.swallowff.modules.core.listener;

import cn.swallowff.modules.core.chat.ChatUserService;
import cn.swallowff.modules.core.util.SpringContextHolder;
import cn.swallowff.modules.demo.io.tio.usecase.server.ChatWebsocketServerStarter;
import cn.swallowff.modules.demo.io.tio.usecase.server.ChatWsMsgHandler;
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

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //do something when spring application started
        //TODO 启动nio聊天服务
        ChatWsMsgHandler chatWsMsgHandler = ChatWsMsgHandler.me;
        chatWsMsgHandler.registerUserService(SpringContextHolder.getBean(ChatUserService.class));
        try {
            ChatWebsocketServerStarter serverStarter = new ChatWebsocketServerStarter(8999,chatWsMsgHandler);
            serverStarter.doStart();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
