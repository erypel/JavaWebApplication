package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * A paymentChannelFund transaction adds XRP to a payment channel and optionally
 * sets a new expiration for the channel.
 * 
 * @author Evan
 *
 */
public class PaymentChannelFund extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void preparePaymentChannelFund()
	{
		// TODO Auto-generated method stub
		
	}
}
