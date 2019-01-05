package com.javawebapp.model.objectsforrippleapi;

/**
 * This class represents the source of a payment transaction
 * @author Evan
 *
 */
public class Source
{
	Address address; //The address to send from.
	Amount amount; //An exact amount to send. If the counterparty is not specified, amounts with any counterparty may be used. (This field is exclusive with source.maxAmount)
	long tag; //Optional An arbitrary unsigned 32-bit integer that identifies a reason for payment or a non-Ripple account.
	Amount maxAmount; //The maximum amount to send. (This field is exclusive with source.amount)
	
	// TODO think about this and iron out kinks
	public Source(Amount amount, long sourceTag)
	{
		this.address = new Address("rwYQjHp9HZiKKpZB4i4fvc8eQvAtA7vdY6"); //TODO a test address for a test account. just want to see if value is transferred.
		this.tag = sourceTag;
		this.amount = amount;
		this.maxAmount = amount; //might want to mess with this
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	public Amount getAmount()
	{
		return amount;
	}
	
	public void setAmount(Amount amount)
	{
		this.amount = amount;
	}
	
	public long getTag()
	{
		return tag;
	}
	
	public void setTag(long tag)
	{
		this.tag = tag;
	}
	
	public Amount getMaxAmount()
	{
		return maxAmount;
	}
	
	public void setMaxAmount(Amount maxAmount)
	{
		this.maxAmount = maxAmount;
	}
}
