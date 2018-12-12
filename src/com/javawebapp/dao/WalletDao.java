package com.javawebapp.dao;

import com.javawebapp.model.Wallet;

public interface WalletDao
{
	public Wallet getWallet(long ownerId);
	
	public Wallet createWallet(long ownerId);
}
