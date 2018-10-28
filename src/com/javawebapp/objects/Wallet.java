package com.javawebapp.objects;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WALLET")
public class Wallet implements Serializable
{
	//TODO HashMap<String, TransactionOutput>
	//THIS MIGHT NOT BE NECESSARY HERE
	//only UTXOs owned by this wallet
	//public HashMap<String, String> UTXOs = new HashMap<String, String>();
	
	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "WALLETID", updatable = false, nullable = false)
	private long walletId;

	@Id
	@Column(name = "OWNERID", updatable = false, nullable = false)
	//this will be the UserId of the owner of this wallet
	private long ownerId;
	
	@Column(name = "PRIVATEKEY")
	private PrivateKey privateKey;
	
	@Column(name = "PUBLICKEY")
	private PublicKey publicKey;
	
	public long getWalletId()
	{
		return walletId;
	}

	public void setWalletId(long walletId)
	{
		this.walletId = walletId;
	}

	public long getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(long ownerId)
	{
		this.ownerId = ownerId;
	}

	public PrivateKey getPrivateKey()
	{
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey)
	{
		this.privateKey = privateKey;
	}

	public PublicKey getPublicKey()
	{
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey)
	{
		this.publicKey = publicKey;
	}
}
