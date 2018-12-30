package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * An order transaction creates a limit order. It defines an intent to exchange
 * currencies, and creates an order in the XRP Ledger's order book if not
 * completely fulfilled when placed. Orders can be partially fulfilled.
 * 
 * @author Evan
 *
 */
public class Order extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void prepareOrder()
	{
		// TODO Auto-generated method stub
		
	}
}
