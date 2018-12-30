package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * An escrowExecution transaction unlocks the funds in an escrow and sends them
 * to the destination of the escrow, but it will only work if the cryptographic
 * condition is provided.
 * 
 * @author Evan
 *
 */
public class EscrowExecution extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void prepareEscrowExecution()
	{
		// TODO Auto-generated method stub
		
	}
}
