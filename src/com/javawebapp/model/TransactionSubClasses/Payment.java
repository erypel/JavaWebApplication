package com.javawebapp.model.TransactionSubClasses;

import com.javawebapp.model.Transaction;

/**
 * A payment transaction represents a transfer of value from one account to
 * another. Depending on the path taken, additional exchanges of value may occur
 * atomically to facilitate the payment.
 * 
 * @author Evan
 *
 */
public class Payment extends Transaction
{

	@Override
	public void prepareTransaction()
	{
		// TODO Auto-generated method stub
		
	}
	
}
