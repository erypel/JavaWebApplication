package com.javawebapp.service;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import com.javawebapp.model.Transaction;
import com.javawebapp.dao.LocalXRPLedgerDao;
import com.javawebapp.dao.impl.LocalXRPLedgerDaoImpl;
import com.javawebapp.model.LocalXRPLedger;

@Component
public class XRPWalletService
{
	LocalXRPLedgerDao dao = new LocalXRPLedgerDaoImpl();
	
	public LocalXRPLedger register(Long userID)
	{
		return dao.createWallet(userID);
	}
	
	public LocalXRPLedger getWallet(Long ownerID)
	{
		return dao.getWallet(ownerID);
	}
	
	public boolean sendXRPInternal()
	{
		return false;
	}
	
	public boolean sendXRPExternal()
	{
		return false;
	}
	
	public BigInteger mapDestinationTag()
	{
		return null;
	}
	
	//check for collisions when mapping. maybe move to hpcUtil
	public boolean collisionCheck()
	{
		return false;
	}
	
	//consider that destination tag valid only for a payment with the expected amount
	public boolean setValid(Transaction tx, double expectedAmount)
	{
		return false;
	}
	
	//send an invalid transaction back
	public boolean bounce(Transaction tx)
	{
		return false;
	}
}
