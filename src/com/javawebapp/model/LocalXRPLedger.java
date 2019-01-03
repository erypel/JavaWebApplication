package com.javawebapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.javawebapp.util.JavaWebAppUtils;

@Entity
@Table(name = "LOCALXRPLEDGER")
/**
 * This is a representation of a user's individual XRP wallet. The platform is
 * architected such that there is one main XRP wallet for everybody and
 * individual balances are kept in the DB since it would be too expensive to
 * create wallets for every single user.
 * 
 * @author Evan
 *
 */
//TODO a test should be written to make sure the platform is solvent, i.e. check that summed wallet balances are less than total XRP 
public class LocalXRPLedger implements Serializable
{
	@Id
	@Column(name = "WALLETID", updatable = false, nullable = false)
	// The internal wallet id
	private long walletId;
	
	@Id
	@Column(name = "OWNERID", updatable = false, nullable = false)
	// this will be the UserId of the owner of this wallet
	private long ownerId;
	
	@Column(name = "DESTINATION_TAG")
	// Since we are using a shared XRP address, individual users need destination
	// tags
	// to send XRP in our network. These are 9 digits long
	private String destTag;
	
	@Column(name = "SOURCE_TAG")
	private String srcTag;
	
	@ColumnDefault(value = "0.00")
	@Column(name = "FUNDS", nullable = false)
	// This is a string in order to maintain precision
	private String funds;
	
	/**
	 * NOT RECOMMENDED FOR USE
	 */
	public LocalXRPLedger()
	{
		// default constructor.
	}
	
	public LocalXRPLedger(Long ownerId)
	{
		this.ownerId = ownerId;
		this.walletId = JavaWebAppUtils.generateUniqueId(); // TODO phase out
		this.funds = "0.00";
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
	
	public String getDestTag()
	{
		return destTag;
	}
	
	public void setDestTag(String destTag)
	{
		this.destTag = destTag;
	}
	
	public String getSrcTag()
	{
		return srcTag;
	}
	
	public void setSrcTag(String srcTag)
	{
		this.srcTag = srcTag;
	}
	
	public String getFunds()
	{
		return funds;
	}
	
	public void setFunds(String funds)
	{
		this.funds = funds;
	}
	
}
