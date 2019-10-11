package cn.swallowff.modules.demo.io.tio.usecase.server;

import org.tio.server.ServerGroupContext;
import org.tio.utils.jfinal.P;
import org.tio.websocket.server.WsServerStarter;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.io.IOException;

public class ChatWebsocketServerStarter {

	private WsServerStarter wsServerStarter;
	private ServerGroupContext serverGroupContext;

	public ChatWebsocketServerStarter(int port, IWsMsgHandler wsMsgHandler) throws Exception {
		init(port,wsMsgHandler);
	}

	public void init(int port,IWsMsgHandler wsMsgHandler) throws Exception{
		wsServerStarter = new WsServerStarter(port, wsMsgHandler);

		serverGroupContext = wsServerStarter.getServerGroupContext();
		serverGroupContext.setName(ServerConfig.PROTOCOL_NAME);
		serverGroupContext.setServerAioListener(ChatServerAioListener.me);

		//设置ip监控
		serverGroupContext.setIpStatListener(ChatIpStatListener.me);
		//设置ip统计时间段
		serverGroupContext.ipStats.addDurations(ServerConfig.IpStatDuration.IPSTAT_DURATIONS);

		//设置心跳超时时间
		serverGroupContext.setHeartbeatTimeout(ServerConfig.HEARTBEAT_TIMEOUT);

//		if (P.getInt("ws.use.ssl", 1) == 1) {
//			//如果你希望通过wss来访问，就加上下面的代码吧，不过首先你得有SSL证书（证书必须和域名相匹配，否则可能访问不了ssl）
////			String keyStoreFile = "classpath:config/ssl/keystore.jks";
////			String trustStoreFile = "classpath:config/ssl/keystore.jks";
////			String keyStorePwd = "214323428310224";
//
//
//			String keyStoreFile = P.get("ssl.keystore", null);
//			String trustStoreFile = P.get("ssl.truststore", null);
//			String keyStorePwd = P.get("ssl.pwd", null);
//			serverGroupContext.useSsl(keyStoreFile, trustStoreFile, keyStorePwd);
//		}
	}

	public static void start() throws Exception {
		ChatWebsocketServerStarter appStarter = new ChatWebsocketServerStarter(ServerConfig.SERVER_PORT, ChatWsMsgHandler.me);
		appStarter.wsServerStarter.start();
	}

	public void doStart() throws Exception{
		wsServerStarter.start();
	}

	public ServerGroupContext getServerGroupContext() {
		return serverGroupContext;
	}

	public WsServerStarter getWsServerStarter() {
		return wsServerStarter;
	}
	
	public static void main(String[] args) throws Exception {
		P.use("app.properties");
		start();
	}

}
