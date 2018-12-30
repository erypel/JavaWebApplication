package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * A checkCash transaction redeems a Check to receive up to the amount
 * authorized by the corresponding checkCreate transaction. Only the destination
 * address of a Check can cash it.
 * 
 * @author Evan
 *
 */
public class CheckCash extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void prepareCheckCash()
	{
		// TODO Auto-generated method stub
		
	}
	
}
