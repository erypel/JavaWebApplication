package com.javawebapp;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javawebapp.objects.Wallet;

@Controller
@RequestMapping("/WalletController")
public class WalletController
{
	@Autowired
	private WalletService service;
	
	@RequestMapping(value = "/createWallet/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public WalletService createWallet(@PathVariable("userId") Long userId)
	{
		WalletService ws = generateWallet();
		ws.setOwnerId(userId);
		return ws;
	}
	
	public static WalletService generateWallet()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		WalletService wallet = (WalletService) context.getBean("walletService");
		return wallet;
	}
	
	public static void generateKeyPair(WalletService wallet)
	{
		// necessary to add manually in a PC environment
		// this is needed for the KeyPairGenerator
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			// Initialize the key generator and generate a KeyPair
			keyGen.initialize(ecSpec, random); // 256 bytes provides an acceptable security level
			KeyPair keyPair = keyGen.generateKeyPair();
			((WalletService) wallet).setPrivateKey(keyPair.getPrivate());
			((WalletService) wallet).setPublicKey(keyPair.getPublic());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
