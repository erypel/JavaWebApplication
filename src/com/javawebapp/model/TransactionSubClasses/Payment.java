package com.javawebapp.model.TransactionSubClasses;

import org.json.simple.JSONObject;

import com.javawebapp.model.Transaction;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

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
		return p;
	}
}
