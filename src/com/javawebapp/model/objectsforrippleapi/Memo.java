package com.javawebapp.model.objectsforrippleapi;

/**
 * Every transaction can optionally have an array of memos for user
 * applications. The memos field in each transaction specification is an array
 * of objects with the following structure:
 * 
 * @author Evan
 *
 */
public class Memo
{
	String data; // Optional Arbitrary string, conventionally containing the content of the memo.
	String format; // Optional Conventionally containing information on how the memo is encoded,
					// for example as a MIME type. Only characters allowed in URLs are permitted.
	String type; // Optional Conventionally, a unique relation (according to RFC 5988) that
					// defines the format of this memo. Only characters allowed in URLs are
					// permitted
	
	public String getData()
	{
		return data;
	}
	
	public void setData(String data)
	{
		this.data = data;
	}
	
	public String getFormat()
	{
		return format;
	}
	
	public void setFormat(String format)
	{
		this.format = format;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
}
