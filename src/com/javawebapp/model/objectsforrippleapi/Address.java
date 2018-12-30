package com.javawebapp.model.objectsforrippleapi;
/**
 * a class representation of an XRP address
 * 
 * Every XRP Ledger account has an address, which is a base58-encoding of a hash
 * of the account's public key. XRP Ledger addresses always start with the
 * lowercase letter r
 * 
 * @author Evan
 *
 */
public class Address
{
	String address;
	Sequence sequence; // keeps track of the number of transactions made by this account
	
	public Address(String address)
	{
		this.address = address;
		this.sequence = new Sequence(address);
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public Sequence getSequence()
	{
		return sequence;
	}

	@Override
	public String toString()
	{
		return address;
	}
	
}
