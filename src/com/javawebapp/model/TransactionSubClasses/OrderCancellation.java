package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * An orderCancellation transaction cancels an order in the XRP Ledger's order
 * book.
 * 
 * @author Evan
 *
 */
public class OrderCancellation extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void prepareOrderCancellation()
	{
		// TODO Auto-generated method stub
		
	}
}