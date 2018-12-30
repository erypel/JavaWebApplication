package com.javawebapp.model.objectsforrippleapi;

public class Value
{
	String value; //The quantity of the currency, denoted as a string to retain floating
	// point precision
	
	public Value(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
}
