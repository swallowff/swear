package cn.swallowff.modules.niospringboot.server;

import org.tio.server.ServerGroupContext;
import org.tio.utils.jfinal.P;
import org.tio.websocket.server.WsServerStarter;

import java.io.IOException;

/**
 * @author tanyaowu
 * 2017年6月28日 下午5:34:04
 */
public class HelloWebsocketServerStarter {

	private WsServerStarter wsServerStarter;
	private ServerGroupContext serverGroupContext;

	/**
	 *
	 * @author tanyaowu
	 */
	public HelloWebsocketServerStarter(int port, ServerWsMsgHandler wsMsgHandler) throws Exception {
		wsServerStarter = new WsServerStarter(port, wsMsgHandler);

		serverGroupContext = wsServerStarter.getServerGroupContext();
		serverGroupContext.setName(ServerConfig.PROTOCOL_NAME);
		serverGroupContext.setServerAioListener(HelloServerAioListener.me);

		//设置ip监控
		serverGroupContext.setIpStatListener(HelloIpStatListener.me);
		//设置ip统计时间段
		serverGroupContext.ipStats.addDurations(ServerConfig.IpStatDuration.IPSTAT_DURATIONS);
		
		//设置心跳超时时间
		serverGroupContext.setHeartbeatTimeout(ServerConfig.HEARTBEAT_TIMEOUT);
		
		if (P.getInt("ws.use.ssl", 1) == 1) {
			//如果你希望通过wss来访问，就加上下面的代码吧，不过首先你得有SSL证书（证书必须和域名相匹配，否则可能访问不了ssl）
//			String keyStoreFile = "classpath:config/ssl/keystore.jks";
//			String trustStoreFile = "classpath:config/ssl/keystore.jks";
//			String keyStorePwd = "214323428310224";
			
			
			String keyStoreFile = P.get("ssl.keystore", null);
			String trustStoreFile = P.get("ssl.truststore", null);
			String keyStorePwd = P.get("ssl.pwd", null);
			serverGroupContext.useSsl(keyStoreFile, trustStoreFile, keyStorePwd);
		}
	}

	/**
	 * @author swallowff
	 * @throws IOException
	 */
	public static void start() throws Exception {
		HelloWebsocketServerStarter appStarter = new HelloWebsocketServerStarter(ServerConfig.SERVER_PORT, ServerWsMsgHandler.me);
		appStarter.wsServerStarter.start();
	}

	/**
	 * @return the serverGroupContext
	 */
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
