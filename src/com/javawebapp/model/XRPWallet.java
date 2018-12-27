package com.javawebapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XRPWALLET")
public class XRPWallet implements Serializable
{
	@Id
	@Column(name = "WALLETID", updatable = false, nullable = false)
	//The internal wallet id
	private long walletId;
	
	@Id
	@Column(name = "OWNERID", updatable = false, nullable = false)
	//this will be the UserId of the owner of this wallet
	private long ownerId;
	
	@Id
	@Column(name ="DESTINATION_TAG", updatable = false, nullable = false)
	// Since we are using a shared XRP address, individual users need destination tags
	// to send XRP in our network. These are 9 digits long
	private long destTag;
	
	/**
	 * NOT RECOMMENDED FOR USE
	 */
	public XRPWallet()
	{
		//default constructor. 
	}
	
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

	public long getDestTag()
	{
		return destTag;
	}

	public void setDestTag(long destTag)
	{
		this.destTag = destTag;
	}

}
