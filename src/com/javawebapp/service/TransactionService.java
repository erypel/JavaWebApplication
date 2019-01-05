package com.javawebapp.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.javawebapp.constants.TransactionConstants;
import com.javawebapp.factory.base.BaseSpecificationFactory;
import com.javawebapp.factory.base.BaseTransactionFactory;
import com.javawebapp.factory.impl.SpecificationFactory;
import com.javawebapp.factory.impl.TransactionFactory;
import com.javawebapp.model.Transaction;
import com.javawebapp.model.TransactionSubClasses.PaymentTransaction;
import com.javawebapp.model.objectsforrippleapi.Amount;
import com.javawebapp.model.objectsforrippleapi.Destination;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Payment;
import com.javawebapp.model.objectsforrippleapi.Promise;
import com.javawebapp.model.objectsforrippleapi.Source;
import com.javawebapp.model.objectsforrippleapi.Value;

@Component
public class TransactionService
{
	BaseTransactionFactory transactionFactory = new TransactionFactory();
	BaseSpecificationFactory specificationFactory = new SpecificationFactory();
	
	//TODO ripped this all from PaymentTransaction.java for testing
	public Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions)
	{
		Promise<Object> p = preparePayment(address, (Payment)specification, instructions);
		return p;
	}
	
	public Promise<Object> preparePayment(String address, Payment payment, Instructions instructions)
	{
		JSONObject json = buildPaymentJson(address, payment, instructions);
		Promise<Object> p = new Promise<Object>();
		p.setTxJSON(json.toJSONString());
		p.setInstructions(instructions);
		p.setInstructionsFee(new Amount(instructions.getFee())); //TODO this only works for drops right now
		p.setInstructionsSequence(instructions.getSequence());
		p.setInstructionsMaxLedgerVersionInteger(instructions.getMaxLedgerVersionInteger());
		p.setInstructionsMaxLedgerVersionString(instructions.getMaxLedgerVersionString());
		return p;
	}
	
	public JSONObject buildPaymentJson(String address, Payment payment, Instructions instructions)
	{
		PaymentTransaction p = new PaymentTransaction();
		JSONObject json = p.buildCommonFieldsJson();
		json = instantiateCommonTransactionFields(json, address, instructions);
		json = addDestination(json, payment);
		return json;
	}

	private JSONObject addDestination(JSONObject json, Payment payment)
	{
		json.put(TransactionConstants.DESTINATION, payment.getDestination().getAddress().toString());
		return json;
	}

	public JSONObject instantiateCommonTransactionFields(JSONObject json, String address, Instructions instructions)
	{
		json.replace(TransactionConstants.ACCOUNT, address);
		json.replace(TransactionConstants.TRANSACTION_TYPE, TransactionConstants.PAYMENT);
		json.put(TransactionConstants.FEE, instructions.getFee().toString()); // fee is in drops
		json.put(TransactionConstants.SEQUENCE, new Integer(instructions.getSequence().getSequence()));
		
		String acctTxnID = lookupAcctTxnID(address);
		json.replace(TransactionConstants.ACCOUNT_TXN_ID, acctTxnID);
		json.replace(TransactionConstants.FLAGS, getFlags());
		json.put(TransactionConstants.LAST_LEDGER_SEQUENCE, instructions.getMaxLedgerVersionString()); //https://developers.ripple.com/reliable-transaction-submission.html
		json.put(TransactionConstants.MEMOS, null);
		json.put(TransactionConstants.SIGNERS, null);
		json.put(TransactionConstants.SOURCE_TAG, getSourceTag(address));
		
		return json;
	}

	private Object getSourceTag(String address)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private Object getFlags()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private String lookupAcctTxnID(String address)
	{
		// TODO Auto-generated method stub
		return null;
	}
	// END TESTING STUFF
	
	// BELOW METHODS DEFINITELY BELONG HERE
	/**
	 * Creates a payment transaction. For right now, this is just being used to send 1 xrp transactions between 2 addresses.
	 * @param sourceTag: an identifier for the sender
	 * @param destinationTag: an identifier for the receiver 
	 * @param amount: the amount to send
	 * @return
	 */
	public Transaction createPaymentTransaction(long sourceTag, long destinationTag, String amount)
	{
		Transaction payment = transactionFactory.createTransaction(TransactionConstants.PAYMENT);
		Source source = new Source(new Amount(new Value(amount)), sourceTag); //TODO this is way to tricky. make simpler with factory or something
		Destination destination = new Destination(new Amount(new Value(amount)), destinationTag);
		// expecting a payment specification object here
		Payment specification = (Payment) specificationFactory.createSpecification(TransactionConstants.PAYMENT, source, destination);
		payment.setSpecification(specification);
		
		Instructions instructions = new Instructions(); //instructions are optional, so not implementing yet
		payment.setInstructions(instructions);
		return payment;
	}
}
