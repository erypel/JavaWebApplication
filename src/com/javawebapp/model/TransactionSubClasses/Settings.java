package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * A settings transaction modifies the settings of an account in the XRP Ledger.
 * 
 * @author Evan
 *
 */
public class Settings extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void prepareSettings()
	{
		// TODO Auto-generated method stub
		
	}
}
