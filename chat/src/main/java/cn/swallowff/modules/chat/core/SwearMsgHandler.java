package cn.swallowff.modules.chat.core;

import cn.swallowff.common.json.GsonHelper;
import cn.swallowff.common.lang.StringUtils;
import cn.swallowff.modules.chat.custom.inf.UserService;
import cn.swallowff.modules.chat.conf.ServerConfig;
import cn.swallowff.modules.chat.custom.SwearSocketRequest;
import cn.swallowff.modules.chat.custom.SwearSocketResponse;
import cn.swallowff.modules.chat.custom.User;
import cn.swallowff.modules.chat.custom.msgpacket.RespMsgPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.Objects;

/**
 * @author swallowff
 * @created 2019年6月28日
 */
public class SwearMsgHandler implements IWsMsgHandler {
	private static Logger log = LoggerFactory.getLogger(SwearMsgHandler.class);

	private UserService userService;   //由客户端实现

	public static final SwearMsgHandler me = new SwearMsgHandler();

	private SwearMsgHandler() {}

	public void registerUserService(UserService service){
		this.userService = service;
	}

	/**
	 * 握手时走这个方法，业务可以在这里获取cookie，request参数等
	 */
	@Override
	public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
		String clientip = httpRequest.getClientIp();
        String userid = httpRequest.getParam("userid");   //userid必传
        if (StringUtils.isBlank(userid)){
            channelContext.setClosed(true);
        }else {
            log.info("收到来自{}的握手包 \r\n{}", clientip, httpRequest.toString());
        }
		return httpResponse;
	}

	@Override
	public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        String userid = httpRequest.getParam("userid");
        Tio.bindUser(channelContext, userid);
        channelContext.setUserid(userid);

		//绑定到群组
		Tio.bindGroup(channelContext,ServerConfig.GROUP_ID);
		int count = Tio.getAllChannelContexts(channelContext.groupContext).getObj().size();
		SwearSocketResponse cusSocketResponse = SwearSocketResponse.newSuccess();
		//用tio-websocket，服务器发送到客户端的Packet都是WsResponse
		WsResponse wsResponse = WsResponse.fromText(cusSocketResponse.toJson(), ServerConfig.CHARSET);
		Tio.send(channelContext,wsResponse);
	}

	/**
	 * 字节消息（binaryType = arraybuffer）过来后会走这个方法
	 */
	@Override
	public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
		String str = new String(bytes);
		log.info("收到byte消息{},消息解析后为{}",bytes,str);
		return null;
	}

	/**
	 * 当客户端发close flag时，会走这个方法
	 */
	@Override
	public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
		Tio.remove(channelContext, "receive close flag");
		return null;
	}

	/**
	 * 字符消息（binaryType = blob）过来后会走这个方法
	 */
	@Override
	public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.get();
		HttpRequest httpRequest = wsSessionContext.getHandshakeRequest();

		if (log.isInfoEnabled()){
			log.info("收到客户端{}消息:{}",channelContext.userid, text);
		}
		//心跳消息直接返回
		if (Objects.equals("Heartbeat", text)) {
			log.info("{}心跳正常",channelContext.userid);
			return null;
		}

		//非心跳消息,解析成约定的请求格式
		WsResponse wsResponse = null;
		SwearSocketRequest socketRequest = GsonHelper.parseJson(text,SwearSocketRequest.class);
		if (null == socketRequest){
		    //不是指定的消息格式
			wsResponse = WsResponse.fromText(SwearSocketResponse.newError().toJson(),ServerConfig.CHARSET);
			return wsResponse;
		}
		//模拟响应数据
		String touser = (String) socketRequest.getBody().get("to");
//		User targetUser = userService.getUser(touser);
		User sender = userService.getUser(channelContext.userid);

		String content = (String) socketRequest.getBody().get("content");
		SwearSocketResponse webSocketResponse = SwearSocketResponse.newSuccess();
		RespMsgPacket respMsgPack = new RespMsgPacket(touser,"friend",content);
		if (null != sender){
			respMsgPack.setAvatar(sender.getAvatar());
		}
		respMsgPack.setFrom(channelContext.userid);
		respMsgPack.setUsername(sender.getUsername());
		webSocketResponse.setData(respMsgPack);

		wsResponse = WsResponse.fromText(webSocketResponse.toJson(),ServerConfig.CHARSET);
		Tio.sendToUser(channelContext.getGroupContext(),touser,wsResponse);
//		channelContext.getToken();
		//群发
//		Tio.sendToGroup(channelContext.groupContext, Const.GROUP_ID, wsResponse);

		//返回值是要发送给客户端的内容，一般都是返回null
		return null;
	}

}
