package com.javawebapp.exception;

public class InsufficientFundsException extends Exception
{
	public InsufficientFundsException(long senderUserID)
	{
		super("User " + senderUserID + "has insufficent funds to send.");
	}
}
