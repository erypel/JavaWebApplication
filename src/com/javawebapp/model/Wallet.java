package com.javawebapp.model;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.javawebapp.util.JavaWebAppUtils;

//TODO convert this to an XRP wallet and integrate interledger
@Entity
@Table(name = "WALLET")
public class Wallet implements Serializable
{
	//TODO HashMap<String, TransactionOutput>
	//THIS MIGHT NOT BE NECESSARY HERE
	//only UTXOs owned by this wallet
	//public HashMap<String, String> UTXOs = new HashMap<String, String>();
	
	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "WALLETID", updatable = false, nullable = false)
	private long walletId;

	@Id
	@Column(name = "OWNERID", updatable = false, nullable = false)
	//this will be the UserId of the owner of this wallet
	private long ownerId;
	
	//TODO change to encoded byte[]
	@Column(name = "PRIVATEKEY")
	private String privateKey;
	
	//TODO change to encoded bye[]
	@Column(name = "PUBLICKEY")
	private String publicKey;
	
	/**
	 * NOT RECOMMENDED FOR USE
	 */
	public Wallet()
	{
		//default constructor. not 
	}
	
	/**
	 * RECOMMENDED FOR USE
	 */
	public Wallet(long ownerId)
	{
		this.ownerId = ownerId;
		this.walletId = JavaWebAppUtils.generateUniqueId(); //TODO the db can do this
		generateKeyPair();
	}
	
	public long getWalletId()
	{
		return walletId;
	}

	public void setWalletId(long walletId)
	{
		this.walletId = walletId;
	}

	public long getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(long ownerId)
	{
		this.ownerId = ownerId;
	}

	public String getPrivateKey()
	{
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey)
	{
		this.privateKey = privateKey.toString();
	}

	public String getPublicKey()
	{
		
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey)
	{
		this.publicKey = publicKey.toString();
	}
	
	/**
	 * uses Java.security.KeyPairGenerator to generate an Elliptic 
	 * Curve KeyPair.
	 */
	public void generateKeyPair() {
		// necessary to generate key pairs using BC
		if(Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null)
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			// Initialize the key generator and generate a KeyPair
			keyGen.initialize(ecSpec, random); // 256 bytes provides an acceptable security level
			KeyPair keyPair = keyGen.generateKeyPair();
			privateKey = keyPair.getPrivate().toString();
			publicKey = keyPair.getPublic().toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
