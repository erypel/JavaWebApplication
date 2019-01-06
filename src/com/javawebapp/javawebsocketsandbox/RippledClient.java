package com.javawebapp.javawebsocketsandbox;

import java.io.IOException;

public class RippledClient
{
	public static void main(String[] args) throws IOException
	{
		RippledEndpoint re = new RippledEndpoint();
		re.sendMessage("{\"id\": 1,\"command\": \"ping\"}");
		int test = 1;
		test++;
		test++;
		re.closeConnection();
	}
}
