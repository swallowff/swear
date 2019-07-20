package cn.swallowff.modules.demo.io.tio.usecase.server;

import cn.swallowff.common.json.GsonHelper;
import cn.swallowff.modules.demo.io.tio.usecase.common.Const;
import cn.swallowff.modules.demo.io.tio.usecase.common.WebSocketRequest;
import cn.swallowff.modules.demo.io.tio.usecase.common.WebSocketResponse;
import cn.swallowff.modules.demo.io.tio.usecase.server.pack.RespMsgPack;
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
public class ChatWsMsgHandler implements IWsMsgHandler {
	private static Logger log = LoggerFactory.getLogger(ChatWsMsgHandler.class);

	public static final ChatWsMsgHandler me = new ChatWsMsgHandler();

	private ChatWsMsgHandler() {

	}

	/**
	 * 握手时走这个方法，业务可以在这里获取cookie，request参数等
	 */
	@Override
	public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
		String clientip = request.getClientIp();
		String userid = request.getParam("userid");
		
		Tio.bindUser(channelContext, userid);
		channelContext.setUserid(userid);
		log.info("收到来自{}的ws握手包 \r\n{}", clientip, request.toString());
		return httpResponse;
	}

	@Override
	public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
		//绑定到群组，后面会有群发
		Tio.bindGroup(channelContext, Const.GROUP_ID);
		int count = Tio.getAllChannelContexts(channelContext.groupContext).getObj().size();
//		String msg = "{name:'admin',message:'" + channelContext.userid + " 进来了，共【" + count + "】人在线" + "'}";
		WebSocketResponse webSocketResp = WebSocketResponse.newSuccess();
//		webSocketResp.putData(msg);
		//用tio-websocket，服务器发送到客户端的Packet都是WsResponse
		WsResponse wsResponse = WsResponse.fromText(webSocketResp.toJson(), "UTF-8");
		Tio.send(channelContext,wsResponse);
		//群发
//		Tio.sendToGroup(channelContext.groupContext, Const.GROUP_ID, wsResponse);
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
			return null;
		}

		//非心跳消息,解析成约定的请求格式
		WsResponse wsResponse = null;
		WebSocketRequest socketRequest = GsonHelper.parseJson(text,WebSocketRequest.class);
		if (null == socketRequest){
			wsResponse = WsResponse.fromText(WebSocketResponse.newError().toJson(),ServerConfig.CHARSET);
			return wsResponse;
		}
//		String touser = socketRequest.getBody().getTo();
		//模拟响应数据
		String touser = "221341842032168960";
		String content = (String) socketRequest.getBody().get("content");
		WebSocketResponse webSocketResponse = WebSocketResponse.newSuccess();
		RespMsgPack respMsgPack = new RespMsgPack(touser,"text",content);
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
