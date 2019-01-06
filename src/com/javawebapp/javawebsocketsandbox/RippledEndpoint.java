package com.javawebapp.javawebsocketsandbox;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class RippledEndpoint
{
	private Session session;
	private static Set<RippledEndpoint> rippledEndpoints = new CopyOnWriteArraySet<>();
	private static HashMap<String, String> users;
	
	/**
	 *  Contains the web socket to interact with XRP ledger API found here:
	 *  https://developers.ripple.com/websocket-api-tool.html
	 */

	// Production Web Socket
	//let webSocket = new WebSocket("wss://s2.ripple.com:443 ");
	// TestNet web socket
	/**
	 * Test Credentials:
	 * 
	 * Public Address:
	 * rwYQjHp9HZiKKpZB4i4fvc8eQvAtA7vdY6
	 * 
	 * Private Key:
	 * snKixQChzs9KcBxxrYWpm97sxnA1e
	 */

	/**
	 * Second set of test credentials
	 * 
	 * Address 
	 * rntmtrrtSGS9dJD84krKvutJLeQ6mADgQp 
	 * Secret
	 * snzzPQHtaCt2oj6YEx5CmCLD3p9Qv 
	 * Balance 10,000 XRP
	 */
	//Java implementation is ws, not wss, and must end with a '/'
	private String rippledURI = "wss://s.altnet.rippletest.net:51233/";
	
	public RippledEndpoint()
	{
		try
		{
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, URI.create("https://s.altnet.rippletest.net:51234/"));
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@OnOpen
	public void onOpen(Session session) throws IOException
	{
		//Get session and websocket connection
		System.out.println("Opening Rippled Websocket");
		this.session = session;
	}
	
	@OnMessage
	public void onMessage(Session session, String message) throws IOException
	{
		//Handle new messages
		System.out.println("Received message:\n" + message);
	}
	
	@OnClose
	public void onClose(Session session) throws IOException
	{
		// WebSocket connection closes
		System.out.println("Closing Rippled Websocket");
		this.session = null;
	}
	
	@OnError
	public void onError(Session session, Throwable throwable)
	{
		//Do error handling here
		throwable.printStackTrace();
	}
	
	public void sendMessage(String message)
	{
		System.out.println("Message sent to server: " + message);
		this.session.getAsyncRemote().sendText(message);
	}
	
	public void closeConnection() throws IOException
	{
		this.session.close();
	}
}
