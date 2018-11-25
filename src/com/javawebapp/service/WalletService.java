package com.javawebapp.service;

import org.springframework.stereotype.Component;

import com.javawebapp.dao.impl.WalletDaoImpl;
import com.javawebapp.model.Wallet;

@Component
public class WalletService
{
	WalletDaoImpl walletDao = new WalletDaoImpl();
	
	public Wallet getWallet(long ownerId)
	{
		return walletDao.getWallet(ownerId);
	}
	
	public Wallet register(long ownerId)
	{
		return walletDao.createWallet(ownerId);
	}
}
