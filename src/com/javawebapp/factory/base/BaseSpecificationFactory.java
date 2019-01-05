package com.javawebapp.factory.base;

import com.javawebapp.model.objectsforrippleapi.Destination;
import com.javawebapp.model.objectsforrippleapi.Source;

public abstract class BaseSpecificationFactory
{
	public abstract Object createSpecification(String type, Source source, Destination destination);
}
