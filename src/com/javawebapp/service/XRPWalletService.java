package com.javawebapp.service;

import java.io.IOException;

import javax.script.ScriptException;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.SignedTransaction;
import com.javawebapp.util.HastyPuddingCipherUtil;
import com.javawebapp.util.JavaWebAppUtils;
import com.javawebapp.dao.LocalXRPLedgerDao;
import com.javawebapp.dao.impl.LocalXRPLedgerDaoImpl;
import com.javawebapp.exception.InsufficientFundsException;
import com.javawebapp.model.LocalXRPLedger;

@Component
public class XRPWalletService
{
	TransactionService transactionService = new TransactionService();
	LocalXRPLedgerDao dao = new LocalXRPLedgerDaoImpl();
	
	public LocalXRPLedger register(Long userID)
	{
		return dao.createWallet(userID);
	}
	
	public LocalXRPLedger getWallet(Long ownerID)
	{
		return dao.getWallet(ownerID);
	}
	
	public String getWalletBalance(Long ownerID)
	{
		return dao.getWalletBalance(ownerID);
	}
	
	/**
	 * Send xrp to an internal destination tag. Right now this is only implemented for tipping
	 * @param fromUserID
	 * @param toUserID
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public Transaction createXRPTipTransaction(long fromUserID, long toUserID, String amount) throws Exception
	{
		//TODO uncomment for real usage
		/*String fromUserBalance = getWalletBalance(fromUserID);
		if(Double.parseDouble(fromUserBalance) - Double.parseDouble(amount) < 0)
			throw new InsufficientFundsException(fromUserID);
		*/
		long sourceTag = mapSourceTag(fromUserID);
		long destinationTag = mapDestinationTag(toUserID);
		
		Transaction tip = transactionService.createPaymentTransaction(sourceTag, destinationTag, amount);	
		return tip;
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
	
	public SignedTransaction signTransaction(Transaction transaction) throws NoSuchMethodException, ScriptException, IOException
	{
		//build transaction json
		JSONObject txJSON = transactionService.buildPaymentJson(transaction);
		String secret = transactionService.getPaymentSecretKey(transaction);
		SignedTransaction signedTx = (SignedTransaction) JavaWebAppUtils.invokeTransactionMethodJavaScript("sign", txJSON.toString(), secret);
		return signedTx;
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
