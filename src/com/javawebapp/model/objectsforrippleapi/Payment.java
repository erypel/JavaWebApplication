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
	Source source;
	Destination destination;
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
	
	public void setSource(Source source)
	{
		this.source = source;
	}
	
	public Object getDestination()
	{
		return destination;
	}
	
	public void setDestination(Destination destination)
	{
		this.destination = destination;
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
