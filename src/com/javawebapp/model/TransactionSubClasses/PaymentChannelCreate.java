package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * A paymentChannelCreate transaction opens a payment channel between two
 * addresses with XRP set aside for asynchronous payments.
 * 
 * @author Evan
 *
 */
public class PaymentChannelCreate extends Transaction
{
	@Override
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void preparePaymentChannelCreate()
	{
		// TODO Auto-generated method stub
		
	}

}
