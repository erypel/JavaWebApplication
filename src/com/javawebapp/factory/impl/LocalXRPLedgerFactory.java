package com.javawebapp.factory.impl;

import com.javawebapp.base.factory.BaseLocalXRPLedgerFactory;
import com.javawebapp.model.LocalXRPLedger;

public class LocalXRPLedgerFactory extends BaseLocalXRPLedgerFactory
{

	@Override
	public LocalXRPLedger createLocalXRPLedger(Long userId)
	{
		return new LocalXRPLedger(userId);
	}
	
}
