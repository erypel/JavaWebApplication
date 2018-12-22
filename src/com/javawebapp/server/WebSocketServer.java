package com.javawebapp.server;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class will server as the web socket server to interact with the XRP
 * ledger. The web socket is available in the URI-space of a web socket server
 * and will be published at the specified URL endpoint (xrpWebWocketEndpoint)
 * 
 * @author Evan
 *
 */
@ServerEndpoint("/xrpWebWocketEndpoint")
public class WebSocketServer
{
	Logger logger = LogManager.getLogger(WebSocketServer.class);
	
	/**
	 * This method is called once per the endpoint and is called once a new
	 * connection is established.
	 */
	@OnOpen
	public void onOpen()
	{
		System.out.println("Open Connection ...");
		logger.info("Open XRP WebSocket Connection ...");
	}
	
	/**
	 * This method is called once per the endpoint and is called when the connection
	 * is being closed.
	 */
	@OnClose
	public void onClose()
	{
		System.out.println("Close Conncetion ...");
		logger.info("Close XRP WebSocket Connection ...");
	}
	
	/**
	 * This is called once a new message is received.
	 * 
	 * @param message
	 * @return
	 */
	@OnMessage
	public String onMessage(String message)
	{
		System.out.println("Message from the client: " + message);
		logger.info("Message from the client: " + message);
		String echoMsg = "Echo from the XRP websocket server: " + message;
		return echoMsg;
	}
	
	/**
	 * This method is called once an exception is being thrown by any method
	 * decorated with @OnOpen, @OnMessage, or @OnClose
	 * 
	 * @param e
	 */
	@OnError
	public void onError(Throwable e)
	{
		e.printStackTrace();
		logger.error(e);
	}
}
