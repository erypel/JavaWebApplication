package com.javawebapp.model;
/**
 * The model for an XRP transaction within the platform.
 * Transactions are created using the Factory Method Pattern
 * @author Evan
 *
 */
public abstract class Transaction
{
	public abstract void prepareTransaction();
	
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
