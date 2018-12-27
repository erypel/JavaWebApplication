package com.javawebapp.service;

import java.math.BigInteger;

/**
 * This is an implementation of the Hasty Pudding Cipher. This algorithm will be
 * used to map User's Wallets to destination tags for the platform's shared XRP
 * wallet. The specs for HPC can be found here:
 * http://richard.schroeppel.name:8015/hpc/hpc-spec
 * 
 * I am using HPC as per the recommendation here:
 * https://developers.ripple.com/become-an-xrp-ledger-gateway.html#generating-source-and-destination-tags
 * 
 * @author Evan
 *
 */
public class HastyPuddingCipherService
{
	final static int bitSize = 64;
	
	// A few internal "random" numbers used in the cipher:
	// Theoretically, BigInteger provides perfect accuracy
	BigInteger PI19 = new BigInteger("3141592653589793238");
	BigInteger E19 = new BigInteger("2718281828459045235");
	BigInteger R220 = new BigInteger("14142135623730950488");
	
	/**
	 * Key Expansion (KX) Tables
	 *
	 * Each subcipher has a KX (key expansion) table, 256 words of 64-bits,
	 * pseudo-randomly generated from the key. All five tables may be computed when
	 * a key is setup, or the tables may be computed when needed. An application
	 * which only used a few blocksizes would need only a subset of the tables. The
	 * same algorithm is used for each KX table, changing only an initialization.
	 * The KX tables are firewalled: knowing a KX table won't help find the original
	 * key, or a KX table for a different subcipher.
	 *
	 * Each KX table is followed by 30 words which are a copy of the first 30 words.
	 * This allows the cipher to reference the tables as if the index were wrapped
	 * mod 256.
	 * 
	 * @param subCipherNumber: the sub-cipher number (from 1 to 5, 1 is HPC-Tiny)
	 *        Hasty Pudding consists of 5 different sub-ciphers. The blocksize
	 *        controls which sub-cipher is used. Each sub-cipher uses its own key
	 *        expansion (KX) table, derived from the key.
	 *
	 *
	 *        HPC-Tiny 0 - 35 bits HPC-Short 36 - 64 bits HPC-Medium 65 - 128 bits
	 *        HPC-Long 129 - 512 bits HPC-Extended 513+ bits
	 * @param keyLength: the key length in bits (a non-negative integer)
	 * @param idx: a pointer to an array containing the key bits
	 */
	public BigInteger[] createKeyExpansionTable(int subCipherNumber, int keyLength, int idx)
	{
		// The Key Expansion Table
		BigInteger KX[] = new BigInteger[256];
		
		// The first 3 words are intialized as so:
		KX[0] = PI19.add(BigInteger.valueOf(subCipherNumber));
		KX[1] = E19.multiply(BigInteger.valueOf(keyLength));
		// TODO should subCipherNumber be the number of bits instead?
		KX[2] = rotateLeft(R220, subCipherNumber, bitSize);
		
		/*
		 * The remaining 253 words of the array are pseudo-randomly filled in with the
		 * equation
		 * 
		 * KX[i] = KX[i - 1] + (KX[i-2] XOR (KX[i-3] right shift 23) XOR (KX[i-3] left
		 * shift 41))
		 */
		for(int i = 3; i < KX.length; i++)
		{
			KX[i] = KX[i - 1].add(KX[i - 2].xor(KX[i - 3].shiftRight(23)).xor(rotateLeft(KX[i - 3], 41, bitSize)));
		}
		
		return KX;
	}
	
	/**
	 * helper method to rotate BigInt values left. Cuts off leading zeros
	 * 
	 * @param value
	 * @param         shift: number of bits to shift. Must be positive.
	 * @param bitSize
	 * @return
	 */
	public static BigInteger rotateLeft(BigInteger value, int shift, int bitSize)
	{
		BigInteger topBits = value.shiftRight(bitSize - shift);
		BigInteger mask = BigInteger.ONE.shiftLeft(bitSize).subtract(BigInteger.ONE);
		return value.shiftLeft(shift).or(topBits).and(mask);
	}
	
	// TODO move testing to another file
	public static void main(String[] args)
	{
		BigInteger val = new BigInteger("14142135623730950488");
		System.out.println(val.toString(2));
		BigInteger rotated = rotateLeft(new BigInteger("14142135623730950488"), 10, bitSize);
		System.out.println(rotated.toString(2));
	}
}
