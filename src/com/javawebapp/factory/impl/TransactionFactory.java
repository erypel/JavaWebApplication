package com.javawebapp.factory.impl;

import com.javawebapp.constants.TransactionConstants;
import com.javawebapp.factory.base.BaseTransactionFactory;
import com.javawebapp.model.Transaction;
import com.javawebapp.model.TransactionSubClasses.CheckCancel;
import com.javawebapp.model.TransactionSubClasses.CheckCash;
import com.javawebapp.model.TransactionSubClasses.CheckCreate;
import com.javawebapp.model.TransactionSubClasses.EscrowCancellation;
import com.javawebapp.model.TransactionSubClasses.EscrowCreation;
import com.javawebapp.model.TransactionSubClasses.EscrowExecution;
import com.javawebapp.model.TransactionSubClasses.Order;
import com.javawebapp.model.TransactionSubClasses.OrderCancellation;
import com.javawebapp.model.TransactionSubClasses.PaymentChannelClaim;
import com.javawebapp.model.TransactionSubClasses.PaymentChannelCreate;
import com.javawebapp.model.TransactionSubClasses.PaymentChannelFund;
import com.javawebapp.model.TransactionSubClasses.PaymentTransaction;
import com.javawebapp.model.TransactionSubClasses.Settings;
import com.javawebapp.model.TransactionSubClasses.Trustline;

public class TransactionFactory extends BaseTransactionFactory
{	
	/**
	 * This method creates a transaction and executes the transaction flow.
	 * Transactions should follow the four steps listed here:
	 * https://developers.ripple.com/rippleapi-reference.html#transaction-flow
	 * they are:
	 * 1. Prepare: create an unsigned transaction based on a specification
	 * 2. Sign: Cryptographically sign the transaction locally and save a
	 * 		transaction ID
	 * 3. Submit: Submits the transaction to the connected server
	 * 4. Verify: verify that the transaction got validated by querying
	 * 		the network. This is necessary because transactions may fail
	 * 		even if they were successfully submitted.
	 */
	@Override
	public Transaction createTransaction(String type) throws IllegalArgumentException
	{
		Transaction tx;
		switch(type)
		{
			case TransactionConstants.CHECK_CANCEL:
				tx = new CheckCancel();
				break;
			case TransactionConstants.CHECK_CASH:
				tx = new CheckCash();
				break;
			case TransactionConstants.CHECK_CREATE:
				tx = new CheckCreate();
				break;
			case TransactionConstants.ESCROW_CANCEL:
				tx = new EscrowCancellation();
				break;
			case TransactionConstants.ESCROW_CREATE:
				tx = new EscrowCreation();
				break;
			case TransactionConstants.ESCROW_EXECUTION:
				tx = new EscrowExecution();
				break;
			case TransactionConstants.ORDER:
				tx = new Order();
				break;
			case TransactionConstants.ORDER_CANCELLATION:
				tx = new OrderCancellation();
				break;
			case TransactionConstants.PAYMENT:
				tx = new PaymentTransaction();
				break;
			case TransactionConstants.PAYMENT_CHANNEL_CLAIM:
				tx = new PaymentChannelClaim();
				break;
			case TransactionConstants.PAYMENT_CHANNEL_CREATE:
				tx = new PaymentChannelCreate();
				break;
			case TransactionConstants.PAYMENT_CHANNEL_FUND:
				tx = new PaymentChannelFund();
				break;
			case TransactionConstants.SETTINGS:
				tx = new Settings();
				break;
			case TransactionConstants.TRUSTLINE:
				tx = new Trustline();
				break;
			default:
				throw new IllegalArgumentException("No such transaction type.");
		}
		
		// TODO consider not doing this automatically in all cases.
		// maybe require a confirmation if a user wants to "tip" a
		// content creator.
		tx.prepareTransaction("", null, null);
		tx.sign();
		tx.submit();
		tx.verify();
		
		return tx;
	}
	
}
