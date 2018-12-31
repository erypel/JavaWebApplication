package com.javawebapp.model.objectsforrippleapi;

/**
 * Every XRP Ledger account has a sequence number that is used to keep
 * transactions in order. Every transaction must have a sequence number. A
 * transaction can only be executed if it has the next sequence number in order,
 * of the account sending it. This prevents one transaction from executing twice
 * and transactions executing out of order. The sequence number starts at 1 and
 * increments for each transaction that the account makes.
 * 
 * @author Evan
 *
 */
public class Sequence
{
	int sequence = 1;
	String account; //The account referencing this sequence
	
	public Sequence(String account)
	{
		this.account = account;
	}
	
	public int increment()
	{
		sequence++;
		return sequence;
	}
	
	public int getSequence()
	{
		return sequence;
	}
}
