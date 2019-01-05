package com.javawebapp.factory.base;

import com.javawebapp.model.Transaction;

public abstract class BaseTransactionFactory
{
	public abstract Transaction createTransaction(String type);
}
