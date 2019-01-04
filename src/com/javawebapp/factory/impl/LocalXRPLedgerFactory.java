package com.javawebapp.factory.impl;

import com.javawebapp.factory.base.BaseLocalXRPLedgerFactory;
import com.javawebapp.model.LocalXRPLedger;

public class LocalXRPLedgerFactory extends BaseLocalXRPLedgerFactory
{

	@Override
	public LocalXRPLedger createLocalXRPLedger(Long userId)
	{
		return new LocalXRPLedger(userId);
	}
	
}
