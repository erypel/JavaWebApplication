package com.javawebapp.model;

import org.json.simple.JSONObject;

import com.javawebapp.constants.TransactionConstants;
import com.javawebapp.model.objectsforrippleapi.Instructions;
import com.javawebapp.model.objectsforrippleapi.Promise;

/**
 * The model for an XRP transaction within the platform.
 * Transactions are created using the Factory Method Pattern
 * @author Evan
 *
 */
public abstract class Transaction
{
	public abstract Promise<Object> prepareTransaction(String address, Object specification, Instructions instructions);
	
	public abstract JSONObject instantiateCommonTransactionFields();
	
	public JSONObject buildCommonFieldsJson()
	{
		JSONObject json = new JSONObject();
		json.put(TransactionConstants.ACCOUNT, "");
		json.put(TransactionConstants.TRANSACTION_TYPE, "");
		json.put(TransactionConstants.FEE, "");
		json.put(TransactionConstants.SEQUENCE, "");
		json.put(TransactionConstants.ACCOUNT_TXN_ID, "");
		json.put(TransactionConstants.FLAGS, "");
		json.put(TransactionConstants.LAST_LEDGER_SEQUENCE, "");
		json.put(TransactionConstants.MEMOS, "");
		json.put(TransactionConstants.SIGNERS, "");
		json.put(TransactionConstants.SOURCE_TAG, "");
		
		// The following two entries aren't needed until signing
		json.put(TransactionConstants.SIGNING_PUBLIC_KEY, "");
		json.put(TransactionConstants.TXN_SIGNATURE, "");
		return json;
	}
	
	public void sign() {
		System.out.println("signed");
	}
	
	public void submit() {
		System.out.println("submitted");
	}
	
	public void verify() {
		System.out.println("verified");
	}
}
