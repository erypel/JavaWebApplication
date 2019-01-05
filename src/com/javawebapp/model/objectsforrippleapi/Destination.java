package com.javawebapp.model.objectsforrippleapi;

/**
 * This class represents the destination of the funds to be sent.
 * @author Evan
 *
 */
public class Destination
{
	Address address; //The address to receive at.
	Amount amount; //An exact amount to deliver to the recipient. If the counterparty is not specified, amounts with any counterparty may be used. (This field cannot be used with destination.minAmount.)
	Long tag; //Optional An arbitrary unsigned 32-bit integer that identifies a reason for payment or a non-Ripple account.
	Amount minAmount; //The minimum amount to be delivered. (This field is exclusive with destination.amount)
	
	public Destination(Amount amount, long destinationTag)
	{
		this.address = new Address("rntmtrrtSGS9dJD84krKvutJLeQ6mADgQp"); //TODO a test address just to see if value is transferred.
		this.amount = amount;
		this.tag = destinationTag;
		this.minAmount = amount;
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
	
	public Amount getMinAmount()
	{
		return minAmount;
	}
	
	public void setMinAmount(Amount minAmount)
	{
		this.minAmount = minAmount;
	}
}
