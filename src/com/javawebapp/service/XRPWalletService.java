package com.javawebapp.service;

import org.springframework.stereotype.Component;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.TransactionSubClasses.PaymentTransaction;
import com.javawebapp.util.HastyPuddingCipherUtil;
import com.javawebapp.constants.TransactionConstants;
import com.javawebapp.dao.LocalXRPLedgerDao;
import com.javawebapp.dao.impl.LocalXRPLedgerDaoImpl;
import com.javawebapp.factory.base.BaseTransactionFactory;
import com.javawebapp.factory.impl.TransactionFactory;
import com.javawebapp.model.LocalXRPLedger;

@Component
public class XRPWalletService
{
	BaseTransactionFactory transactionFactory = new TransactionFactory();
	LocalXRPLedgerDao dao = new LocalXRPLedgerDaoImpl();
	
	public LocalXRPLedger register(Long userID)
	{
		return dao.createWallet(userID);
	}
	
	public LocalXRPLedger getWallet(Long ownerID)
	{
		return dao.getWallet(ownerID);
	}
	
	public boolean sendXRPInternal(long fromUserID, long toUserID, String amount) throws Exception
	{
		long sourceTag = mapSourceTag(fromUserID);
		long destinationTag = mapDestinationTag(toUserID);
		
		Transaction tip = transactionFactory.createTransaction(TransactionConstants.PAYMENT);
		return false;
	}
	
	public boolean sendXRPExternal()
	{
		return false;
	}
	
	/**
	 * Maps a user ID to a destination tag. This is the same logic
	 * as mapping a user ID to a source tag. Using different methods
	 * to promote readability.
	 * 
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public long mapDestinationTag(long userID) throws Exception
	{
		return HastyPuddingCipherUtil.encryptHPCShort(userID);
	}
	
	/**
	 * Maps a user ID to a source tag. This is the same logic
	 * as mapping a user ID to a destination tag. Using different methods
	 * to promote readability.
	 * 
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public long mapSourceTag(long userID) throws Exception
	{
		return HastyPuddingCipherUtil.encryptHPCShort(userID);
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
