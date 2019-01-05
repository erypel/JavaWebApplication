package com.javawebapp.factory.impl;

import com.javawebapp.constants.TransactionConstants;
import com.javawebapp.factory.base.BaseSpecificationFactory;
import com.javawebapp.model.objectsforrippleapi.Destination;
import com.javawebapp.model.objectsforrippleapi.Payment;
import com.javawebapp.model.objectsforrippleapi.Source;

public class SpecificationFactory extends BaseSpecificationFactory
{

	@Override
	public Object createSpecification(String type, Source source, Destination destination)
	{
		Object specification;
		switch(type)
		{
			case TransactionConstants.PAYMENT:
				Payment payment = new Payment();
				payment.setSource(source);
				payment.setDestination(destination);
				specification = payment;
				break;
			default:
				throw new IllegalArgumentException("No such transaction type.");
		}
		return specification;
	}
	
}
