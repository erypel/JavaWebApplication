package com.javawebapp;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

public class WalletController
{
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
