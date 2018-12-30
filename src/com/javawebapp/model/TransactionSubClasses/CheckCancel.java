package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * A checkCancel transaction cancels an unreedemed Check, removing it from the
 * ledger without sending any money.
 * 
 * @author Evan
 *
 */
public class CheckCancel extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void prepareCheckCancel()
	{
		// TODO Auto-generated method stub
		
	}
}
