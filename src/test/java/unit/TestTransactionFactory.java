package test.java.unit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.javawebapp.constants.TransactionConstants;
import com.javawebapp.factory.base.BaseTransactionFactory;
import com.javawebapp.factory.impl.TransactionFactory;
import com.javawebapp.model.Transaction;

public class TestTransactionFactory
{
	@Rule
	public ExpectedException illegalArgumentRule = ExpectedException.none();
	
	@Test
	public void testCreateTransaction()
	{
		BaseTransactionFactory transactionFactory = new TransactionFactory();
		Transaction checkCancel = transactionFactory.createTransaction(TransactionConstants.CHECK_CANCEL);
		Transaction checkCash = transactionFactory.createTransaction(TransactionConstants.CHECK_CASH);
		Transaction checkCreate = transactionFactory.createTransaction(TransactionConstants.CHECK_CREATE);
		Transaction escrowCancellation = transactionFactory.createTransaction(TransactionConstants.ESCROW_CANCEL);
		Transaction escrowCreation = transactionFactory.createTransaction(TransactionConstants.ESCROW_CREATE);
		Transaction escrowExecution = transactionFactory.createTransaction(TransactionConstants.ESCROW_EXECUTION);
		Transaction order = transactionFactory.createTransaction(TransactionConstants.ORDER);
		Transaction orderCancellation = transactionFactory.createTransaction(TransactionConstants.ORDER_CANCELLATION);
		Transaction payment = transactionFactory.createTransaction(TransactionConstants.PAYMENT);
		Transaction paymentChannelClaim = transactionFactory.createTransaction(TransactionConstants.PAYMENT_CHANNEL_CLAIM);
		Transaction paymentChannelCreate = transactionFactory.createTransaction(TransactionConstants.PAYMENT_CHANNEL_CREATE);
		Transaction paymentChannelFund = transactionFactory.createTransaction(TransactionConstants.PAYMENT_CHANNEL_FUND);
		Transaction settings = transactionFactory.createTransaction(TransactionConstants.SETTINGS);
		Transaction trustline = transactionFactory.createTransaction(TransactionConstants.TRUSTLINE);
	}
	
	@Test
	public void testCreateTransactionIllegalArgumentException()
	{
		illegalArgumentRule.expect(IllegalArgumentException.class);
		illegalArgumentRule.expectMessage("No such transaction type.");
		BaseTransactionFactory transactionFactory = new TransactionFactory();
		Transaction error = transactionFactory.createTransaction("error");
	}
}
