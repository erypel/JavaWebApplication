package com.javawebapp.model.TransactionSubClasses;

import org.json.simple.JSONObject;

import com.javawebapp.constants.TransactionConstants;
import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Amount;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Payment;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * A payment transaction represents a transfer of value from one account to
 * another. Depending on the path taken, additional exchanges of value may occur
 * atomically to facilitate the payment.
 * 
 * @author Evan
 *
 */
//TODO split into service and object
public class PaymentTransaction extends Transaction
{
	@Override
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
		JSONObject json = buildCommonFieldsJson();
		json = instantiateCommonTransactionFields(json, address, instructions);
		json = addDestination(json, payment);
		return json;
	}

	private JSONObject addDestination(JSONObject json, Payment payment)
	{
		json.put(TransactionConstants.DESTINATION, payment.getDestinationAddressReceive().toString());
		return json;
	}

	@Override
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
}
