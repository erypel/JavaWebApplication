package test.java.unit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
		Transaction checkCancel = transactionFactory.createTransaction(transactionFactory.CHECK_CANCEL);
		Transaction checkCash = transactionFactory.createTransaction(transactionFactory.CHECK_CASH);
		Transaction checkCreate = transactionFactory.createTransaction(transactionFactory.CHECK_CREATE);
		Transaction escrowCancellation = transactionFactory.createTransaction(transactionFactory.ESCROW_CANCELLATION);
		Transaction escrowCreation = transactionFactory.createTransaction(transactionFactory.ESCROW_CREATION);
		Transaction escrowExecution = transactionFactory.createTransaction(transactionFactory.ESCROW_EXECUTION);
		Transaction order = transactionFactory.createTransaction(transactionFactory.ORDER);
		Transaction orderCancellation = transactionFactory.createTransaction(transactionFactory.ORDER_CANCELLATION);
		Transaction payment = transactionFactory.createTransaction(transactionFactory.PAYMENT);
		Transaction paymentChannelClaim = transactionFactory.createTransaction(transactionFactory.PAYMENT_CHANNEL_CLAIM);
		Transaction paymentChannelCreate = transactionFactory.createTransaction(transactionFactory.PAYMENT_CHANNEL_CREATE);
		Transaction paymentChannelFund = transactionFactory.createTransaction(transactionFactory.PAYMENT_CHANNEL_FUND);
		Transaction settings = transactionFactory.createTransaction(transactionFactory.SETTINGS);
		Transaction trustline = transactionFactory.createTransaction(transactionFactory.TRUSTLINE);
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
