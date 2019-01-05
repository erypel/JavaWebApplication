package com.javawebapp.dao;

import com.javawebapp.model.LocalXRPLedger;

public interface LocalXRPLedgerDao
{
	public LocalXRPLedger getWallet(long ownerId);
	
	public LocalXRPLedger createWallet(long ownerId);

	public String getWalletBalance(long ownerID);
}
