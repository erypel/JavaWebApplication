package com.javawebapp;

import org.springframework.stereotype.Service;
import java.security.PublicKey;
import java.util.HashMap;
import java.security.PrivateKey;

@Service("walletService")
public class WalletService
{
	private long walletId;
	public PrivateKey privateKey;
	public PublicKey publicKey;
	public long ownerId;

	//TODO HashMap<String, TransactionOutput>
	//only UTXOs owned by this wallet
	public HashMap<String, String> UTXOs = new HashMap<String, String>();
	
	public WalletService(long id)
	{
		this.walletId = id;
		WalletController.generateKeyPair(this);
	}
	
	public long getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(long ownerId)
	{
		this.ownerId = ownerId;
	}
	
	public void setId(long id)
	{
		this.walletId = id;
	}
	
	public long getId()
	{
		return this.walletId;
	}
	
	public void setPrivateKey(PrivateKey privateKey)
	{
		this.privateKey = privateKey;
	}
	
	public PrivateKey getPrivateKey()
	{
		return this.privateKey;
	}
	
	public void setPublicKey(PublicKey publicKey)
	{
		this.publicKey = publicKey;
	}
	
	public PublicKey getPublicKey()
	{
		return this.publicKey;
	}
}
