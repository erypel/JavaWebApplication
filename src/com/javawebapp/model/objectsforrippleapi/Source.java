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
