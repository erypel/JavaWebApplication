package com.javawebapp.factory.base;

import com.javawebapp.model.LocalXRPLedger;

public abstract class BaseLocalXRPLedgerFactory
{
	public abstract LocalXRPLedger createLocalXRPLedger(Long userId);
}
