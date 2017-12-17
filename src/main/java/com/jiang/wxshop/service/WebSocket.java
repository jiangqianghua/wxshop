package com.jiang.wxshop.service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jiang.wxshop.controller.SellerOrderController;
/**
 * websocket设置，创建订单通知给网页,先设置WebSocketConfig才有效
 * @author jiangqianghua
 *
 */
@Component
@ServerEndpoint("/webSocket")
public class WebSocket {
	private static Logger logger = LoggerFactory.getLogger(WebSocket.class);
	private Session session ;
	private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
	@OnOpen
	public void onOpen(Session session){
		this.session = session ;
		webSocketSet.add(this);
		logger.info("[websocket]有新的链接，总数{}",webSocketSet.size());
	}
	
	@OnClose
	public void onClose(){
		webSocketSet.remove(this);
		logger.info("[websocket]链接断开，总数{}",webSocketSet.size());
	}
	
	@OnMessage
	public void onMessage(String message){
		logger.info("[websocket] 收到新消息:{}",message);
	}

	public void sendMessage(String message){
		for(WebSocket webSocket:webSocketSet){
			logger.info("[websocket] 广播消息,message={}",message);
			try {
				webSocket.session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
