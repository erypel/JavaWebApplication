package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * A trustline transactions creates or modifies a trust line between two
 * accounts.
 * 
 * @author Evan
 *
 */
public class Trustline extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void prepareTrustline()
	{
		// TODO Auto-generated method stub
		
	}
}
