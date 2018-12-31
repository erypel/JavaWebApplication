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
		JSONObject json = new JSONObject();
		Promise<Object> p = new Promise<Object>();
		p.setTxJSON(json.toJSONString());
		p.setInstructions(instructions);
		p.setInstructionsFee(new Amount(instructions.getFee())); //TODO this only works for drops right now
		p.setInstructionsSequence(instructions.getSequence());
		p.setInstructionsMaxLedgerVersionInteger(instructions.getMaxLedgerVersionInteger());
		p.setInstructionsMaxLedgerVersionString(instructions.getMaxLedgerVersionString());
		return p;
	}
	
	//TODO: the json builder
	public JSONObject buildPaymentJson(String address, Instructions instructions)
	{
		JSONObject json = new JSONObject();
		String account = address; // The unique address of the account that initiated the transaction
		String transactionType = TransactionConstants.PAYMENT;
		String fee = instructions.getFee().toString();
		Integer sequence = instructions.getSequence().getSequence();
		return json;
	}
}
