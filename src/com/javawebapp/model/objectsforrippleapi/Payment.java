package com.javawebapp.model.objectsforrippleapi;

/**
 * A payment transaction represents a transfer of value from one account to
 * another. Depending on the path taken, additional exchanges of value may occur
 * atomically to facilitate the payment.
 * 
 * @author Evan
 *
 */
public class Payment
{
	Object source;
	Address sourceAddress;
	Amount sourceAmount;
	Integer sourceTag;
	Amount sourceMaxAount;
	Object destination;
	Address destinationAddressReceive;
	Amount destinationAmount;
	Integer destinationTag;
	Address destinationAddressSendTo;
	Amount destinationMinAmount;
	boolean allowPartialPayment;
	String invoiceId;
	boolean limitQuality;
	Memo[] memos;
	boolean noDirectRipple;
	String paths;
	
	public Object getSource()
	{
		return source;
	}
	
	public void setSource(Object source)
	{
		this.source = source;
	}
	
	public Address getSourceAddress()
	{
		return sourceAddress;
	}
	
	public void setSourceAddress(Address sourceAddress)
	{
		this.sourceAddress = sourceAddress;
	}
	
	public Amount getSourceAmount()
	{
		return sourceAmount;
	}
	
	public void setSourceAmount(Amount sourceAmount)
	{
		this.sourceAmount = sourceAmount;
	}
	
	public Integer getSourceTag()
	{
		return sourceTag;
	}
	
	public void setSourceTag(Integer sourceTag)
	{
		this.sourceTag = sourceTag;
	}
	
	public Amount getSourceMaxAount()
	{
		return sourceMaxAount;
	}
	
	public void setSourceMaxAount(Amount sourceMaxAount)
	{
		this.sourceMaxAount = sourceMaxAount;
	}
	
	public Object getDestination()
	{
		return destination;
	}
	
	public void setDestination(Object destination)
	{
		this.destination = destination;
	}
	
	public Address getDestinationAddressReceive()
	{
		return destinationAddressReceive;
	}
	
	public void setDestinationAddressReceive(Address destinationAddressReceive)
	{
		this.destinationAddressReceive = destinationAddressReceive;
	}
	
	public Amount getDestinationAmount()
	{
		return destinationAmount;
	}
	
	public void setDestinationAmount(Amount destinationAmount)
	{
		this.destinationAmount = destinationAmount;
	}
	
	public Integer getDestinationTag()
	{
		return destinationTag;
	}
	
	public void setDestinationTag(Integer destinationTag)
	{
		this.destinationTag = destinationTag;
	}
	
	public Address getDestinationAddressSendTo()
	{
		return destinationAddressSendTo;
	}
	
	public void setDestinationAddressSendTo(Address destinationAddressSendTo)
	{
		this.destinationAddressSendTo = destinationAddressSendTo;
	}
	
	public Amount getDestinationMinAmount()
	{
		return destinationMinAmount;
	}
	
	public void setDestinationMinAmount(Amount destinationMinAmount)
	{
		this.destinationMinAmount = destinationMinAmount;
	}
	
	public boolean isAllowPartialPayment()
	{
		return allowPartialPayment;
	}
	
	public void setAllowPartialPayment(boolean allowPartialPayment)
	{
		this.allowPartialPayment = allowPartialPayment;
	}
	
	public String getInvoiceId()
	{
		return invoiceId;
	}
	
	public void setInvoiceId(String invoiceId)
	{
		this.invoiceId = invoiceId;
	}
	
	public boolean isLimitQuality()
	{
		return limitQuality;
	}
	
	public void setLimitQuality(boolean limitQuality)
	{
		this.limitQuality = limitQuality;
	}
	
	public Memo[] getMemos()
	{
		return memos;
	}
	
	public void setMemos(Memo[] memos)
	{
		this.memos = memos;
	}
	
	public boolean isNoDirectRipple()
	{
		return noDirectRipple;
	}
	
	public void setNoDirectRipple(boolean noDirectRipple)
	{
		this.noDirectRipple = noDirectRipple;
	}
	
	public String getPaths()
	{
		return paths;
	}
	
	public void setPaths(String paths)
	{
		this.paths = paths;
	}
}
