package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * An escrowCreation transaction creates an escrow on the ledger, which locks
 * XRP until a cryptographic condition is met or it expires. It is like an
 * escrow service where the XRP Ledger acts as the escrow agent.
 * 
 * @author Evan
 *
 */
public class EscrowCreation extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void prepareEscrowCreation()
	{
		// TODO Auto-generated method stub
		
	}
}
