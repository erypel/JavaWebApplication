package com.javawebapp.model.objectsforrippleapi;

/**
 * This class represents a signed XRP transaction
 * @author Evan
 *
 */
public class SignedTransaction
{
	String signedTransaction;
	String ID; //The transaction hash
	
	public SignedTransaction(String signedTransaction, String ID)
	{
		this.signedTransaction = signedTransaction;
		this.ID = ID;
	}
	
	public String getSignedTransaction()
	{
		return signedTransaction;
	}
	public void setSignedTransaction(String signedTransaction)
	{
		this.signedTransaction = signedTransaction;
	}
	public String getID()
	{
		return ID;
	}
	public void setID(String ID)
	{
		this.ID = ID;
	}
}
