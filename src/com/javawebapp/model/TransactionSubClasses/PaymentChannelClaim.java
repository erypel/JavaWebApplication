package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * A paymentChannelClaim transaction withdraws XRP from a channel and optionally
 * requests to close it.
 * 
 * @author Evan
 *
 */
public class PaymentChannelClaim extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void preparePaymentChannelClaim()
	{
		// TODO Auto-generated method stub
		
	}
}
