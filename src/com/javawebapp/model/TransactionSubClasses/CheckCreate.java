package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * A checkCreate transaction creates a check on the ledger, which is a deferred
 * payment that can be cashed by its intended destination.
 * 
 * @author Evan
 *
 */
public class CheckCreate extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void prepareCheckCreate()
	{
		// TODO Auto-generated method stub
		
	}
}
