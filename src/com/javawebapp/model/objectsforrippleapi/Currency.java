package com.javawebapp.model.objectsforrippleapi;

/**
 * A class representation of a currency
 * 
 * a list of acceptable currency codes can be found here:
 * https://www.xe.com/iso4217.php
 * 
 * @author Evan
 *
 */
public class Currency
{
	String code;
	String countryName;
	
	public Currency(String code)
	{
		this.code = code;
		lookUpCountry(code);
	}
	
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public String getCountryName()
	{
		return countryName;
	}
	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
	}
	
	public void lookUpCountry(String code)
	{
		//TODO store in db https://www.xe.com/iso4217.php
		this.countryName = "";
	}
	
	/**
	 * Solely returns the currency code. User getCountryName()
	 * to get the country's name
	 */
	@Override
	public String toString()
	{
		return this.code;
	}
}
