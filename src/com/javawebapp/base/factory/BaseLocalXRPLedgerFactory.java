package com.javawebapp.base.factory;

import com.javawebapp.model.LocalXRPLedger;

public abstract class BaseLocalXRPLedgerFactory
{
	public abstract LocalXRPLedger createLocalXRPLedger(Long userId);
}
