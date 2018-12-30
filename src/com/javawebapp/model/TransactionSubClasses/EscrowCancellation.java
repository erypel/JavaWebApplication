package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * An escrowCancellation transaction unlocks the funds in an escrow and sends
 * them back to the creator of the escrow, but it will only work after the
 * escrow expires.
 * 
 * @author Evan
 *
 */
public class EscrowCancellation extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void prepareEscrowCancellation()
	{
		// TODO Auto-generated method stub
		
	}
}
