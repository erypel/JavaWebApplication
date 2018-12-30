package com.javawebapp.model;

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
