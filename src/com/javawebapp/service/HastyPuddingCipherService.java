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
	final static int NUM_PASSES = 3; // number of passes for stirring function
	final static int NUM_WORDS = 256;
	final static BigInteger MOD = BigInteger.valueOf(2).pow(64); //applied to all addition, subtraction, multiplication
	
	/*
	 * 	Permb was derived from the hex expansion of e (2.718...).  The
	 *	fraction was grouped into chunks of 64 bits, and the first sixteen
	 *	chunks with unique low-order 4bit hex digits were selected.  The
	 *	twelfth and fourteenth entries would have been fixed points for the
	 *	low-order 4 bits, so they were swapped.
	 */
	static BigInteger[] permb = new BigInteger[] {
		BigInteger.valueOf(0xB7E151628AED2A6Al),
		BigInteger.valueOf(0xBF7158809CF4F3C7l),
		BigInteger.valueOf(0x62E7160F38B4DA56l),
		BigInteger.valueOf(0xA784D9045190CFEFl),
		BigInteger.valueOf(0x324E7738926CFBE5l),
		BigInteger.valueOf(0xF4BF8D8D8C31D763l),
		BigInteger.valueOf(0xDA06C80ABB1185EBl),
		BigInteger.valueOf(0x4F7C7B5757F59584l),
		BigInteger.valueOf(0x90CFD47D7C19BB42l),
		BigInteger.valueOf(0x158D9554F7B46BCEl),
		BigInteger.valueOf(0x8A9A276BCFBFA1C8l),
		BigInteger.valueOf(0xE5AB6ADD835FD1A0l),
		BigInteger.valueOf(0x86D1BF275B9B241Dl),
		BigInteger.valueOf(0xF0D3D37BE67008E1l),
		BigInteger.valueOf(0x0FF8EC6D31BEB5CCl),
		BigInteger.valueOf(0xEB64749A47DFDFB9l)
	};
	static BigInteger[] permbi = new BigInteger[] {
			BigInteger.valueOf(0xE5AB6ADD835FD1A0l),
			BigInteger.valueOf(0xF0D3D37BE67008E1l),
			BigInteger.valueOf(0x90CFD47D7C19BB42l),
			BigInteger.valueOf(0xF4BF8D8D8C31D763l),
			BigInteger.valueOf(0x4F7C7B5757F59584l),
			BigInteger.valueOf(0x324E7738926CFBE5l),
			BigInteger.valueOf(0x62E7160F38B4DA56l),
			BigInteger.valueOf(0xBF7158809CF4F3C7l),
			BigInteger.valueOf(0x8A9A276BCFBFA1C8l),
			BigInteger.valueOf(0xEB64749A47DFDFB9l),
			BigInteger.valueOf(0xB7E151628AED2A6Al),
			BigInteger.valueOf(0xDA06C80ABB1185EBl),
			BigInteger.valueOf(0x0FF8EC6D31BEB5CCl),
			BigInteger.valueOf(0x86D1BF275B9B241Dl),
			BigInteger.valueOf(0x158D9554F7B46BCEl),
			BigInteger.valueOf(0xA784D9045190CFEFl)
		};
	
	//Defaulting to all 0's
	static BigInteger[] spice = new BigInteger[] {
			BigInteger.ZERO,
			BigInteger.ZERO,
			BigInteger.ZERO,
			BigInteger.ZERO,
			BigInteger.ZERO,
			BigInteger.ZERO,
			BigInteger.ZERO,
			BigInteger.ZERO
	};
	
	// A few internal "random" numbers used in the cipher:
	// Theoretically, BigInteger provides perfect accuracy
	static BigInteger PI19 = new BigInteger("3141592653589793238");
	static BigInteger E19 = new BigInteger("2718281828459045235");
	static BigInteger R220 = new BigInteger("14142135623730950488");
	
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
	public static BigInteger[] createKeyExpansionTable(int subCipherNumber, int keyLength, int idx)
	{
		// The Key Expansion Table
		BigInteger KX[] = new BigInteger[NUM_WORDS + 30]; //256 words with wrap-around
		
		// The first 3 words are intialized as so:
		KX[0] = PI19.add(BigInteger.valueOf(subCipherNumber)).mod(MOD);
		KX[1] = E19.multiply(BigInteger.valueOf(keyLength)).mod(MOD);
		// TODO should subCipherNumber be the number of bits instead?
		KX[2] = shiftLeft(R220, subCipherNumber, bitSize);
		
		/*
		 * The remaining 253 words of the array are pseudo-randomly filled in with the
		 * equation
		 * 
		 * KX[i] = KX[i - 1] + (KX[i-2] XOR (KX[i-3] right shift 23) XOR (KX[i-3] left
		 * shift 41))
		 */
		for(int i = 3; i < NUM_WORDS; i++)
		{
			KX[i] = KX[i - 1].add(KX[i - 2].xor(KX[i - 3].shiftRight(23)).xor(shiftLeft(KX[i - 3], 41, bitSize))).mod(MOD);
		}
		
		// TODO? I don't think we'll ever need this many words
		/*
		 * For very long keys: After 128 words of key have been xored into the KX array,
		 * the Stirring function is run. If there is more key to use, the next 128 words
		 * are xored into the stirred KX array, starting over at word 0 of KX. (No key
		 * is ever xored directly into the second half of the KX array, but these bits
		 * are affected by the stirring function.)
		 */
		
		// pseudo randomize the KX array
		stir(KX);
		
		/*
		 * finish up key expansion by copying the first 30 words of the array onto
		 * the end. This allows code that references the KX array to wrap around array
		 * indexes mod 256 without having to mask the index to the low-order 8 bits.
		 */
		for(int i = 0; i < 30; i++)
		{
			KX[256 + i] = KX[i];
		}
		
		
		return KX;
	}
	
	/**
	 * The purpose of the Stirring function is to pseudo-randomize the KX array,
	 * allowing each bit to influence every other bit.
	 * 
	 * The function does several passes of the KX array, altering every word. The
	 * default number of passes is 3. The backup feature causes additional passes.
	 * The number of extra passes is the sum of the global backup variable BACKUP
	 * and the array entry BACKUPSUBCIPHER[0]. Normally both values are 0.
	 */
	public static void stir(BigInteger[] KX)
	{
		/*
		 * The stirring function has 8 internal state variables, each an unsigned 64 bit
		 * word (but that's not how Java works, so we'll cheat a little bit with
		 * BigInteger). Before the first pass of the KX array, they are intialized from
		 * the last 8 values in the array.
		 */
		BigInteger s0 = KX[248], s1 = KX[249], s2 = KX[250], s3 = KX[251], s4 = KX[252], s5 = KX[253], s6 = KX[254],
				s7 = KX[255];
		
		for(int j = 0; j < NUM_PASSES; j++)
		{
			/*
			 * One pass of the KX array: Each word in the KX array is mixed with the state
			 * variables, starting with KX[0] and working through KX[255]. Each array word
			 * is overwritten after mixing. The mising function is deliberately made
			 * slightly lossy so that the process cannot be run backward to discover the
			 * pre-stirred KX value, and hence the key.
			 */
			for(int i = 0; i < NUM_WORDS; i++)
			{
				// Perform the individual word stirring algorithm
				// BigInteger is immutable so need to reassign values like this:
				s0 = s0.xor((KX[i].xor(KX[(i + 83) & 255]).add(KX[s0.and(BigInteger.valueOf(255)).intValue()]).mod(MOD))); // sometimes																							// lossy
				s2 = s2.add(KX[i]).mod(MOD); // necessary to prevent Wagner equivalent key problem
				s1 = s1.add(s0).mod(MOD);
				s3 = s3.xor(s2);
				s5 = s5.subtract(s4).mod(MOD);
				s7 = s7.xor(s6);
				s3 = s3.add(s0.shiftRight(13)).mod(MOD);
				s4 = s4.xor(shiftLeft(s1, 11, bitSize));
				s5 = s5.xor(shiftLeft(s3, s1.and(BigInteger.valueOf(31)).intValue(), bitSize));
				s6 = s6.add(s2.shiftRight(17)).mod(MOD);
				s7 = s7.or(s3.add(s4).mod(MOD)); // lossy
				s2 = s2.subtract(s5).mod(MOD); // cross-link
				s0 = s0.subtract(s6.xor(BigInteger.valueOf(i))).mod(MOD);
				s1 = s1.xor(s5.add(PI19).mod(MOD));
				s2 = s2.add(s7.shiftRight(j)).mod(MOD);
				s2 = s2.xor(s1);
				s4 = s4.subtract(s3).mod(MOD);
				s6 = s6.xor(s5);
				s0 = s0.add(s7).mod(MOD);
				KX[i] = s2.add(s6).mod(MOD);
			}
		}
	}
	
	public static BigInteger HPCShort(Long plaintextDestTag, BigInteger[] KX)
	{
		int blocksize = 64;
		
		/*
		 * 	The plaintext is placed right-justified in variable s0.  LMASK is set
		 *	to a block of 1s, to mask s0 to the valid bits between operations.
		 */
		BigInteger s0 = BigInteger.valueOf(plaintextDestTag);
		BigInteger lmask = BigInteger.valueOf(0xffffffffffffffffl);
		
		// A word from the KX array, KX[blocksize], is added to s0.
		s0 = KX[blocksize].add(s0).mod(MOD);
		s0.and(lmask);
		
		// Several shift sizes are calculated:
		BigInteger LBH = BigInteger.valueOf((blocksize + 1) / 2); //division rounds down
		BigInteger LBQ = (LBH.add(BigInteger.ONE)).divide(BigInteger.valueOf(2));
		BigInteger LBT = (LBQ.add(BigInteger.valueOf(blocksize))).divide(BigInteger.valueOf(4)).add(BigInteger.valueOf(2));
		BigInteger GAP = BigInteger.valueOf(64 - blocksize);
		
		/*
		 * Then 8 rounds of mixing are run with round index i going from 0...7
		 * After the mixing, another word from KX, KX[blocksize + 8] is added to s0
		 * s0 is masked, and the valid bits are written to the output array.
		 * Any high-order bits in the output array are unchanged.
		 */
		for(int i = 0; i < 8; i++)
		{
			BigInteger k = KX[s0.and(BigInteger.valueOf(255)).intValue()].add(spice[i]);
			k.and(lmask);
			s0 = s0.add(k.shiftLeft(8)).mod(MOD);
			s0 = s0.and(lmask);
			BigInteger testing = (k.shiftRight(GAP.intValue())).and(BigInteger.valueOf(~255));
			s0 = s0.xor(testing);
			s0 = s0.and(lmask);
			s0 = s0.add(s0.shiftLeft(LBH.intValue() + i)).mod(MOD);
			
			s0 = s0.and(lmask);
			BigInteger t = spice[(i ^ 7)];
			s0 = s0.xor(t);
			s0 = s0.subtract(t.shiftRight(GAP.intValue() + i)).mod(MOD);
			s0 = s0.add(t.shiftRight(13)).mod(MOD);
			s0 = s0.xor(s0.shiftRight(LBH.intValue()));
			s0 = s0.and(lmask);
			t = s0.and(BigInteger.valueOf(255));
			k = KX[t.intValue()];
			k = k.xor(spice[(i ^ 4)]);
			k.and(lmask);
			k = KX[t.intValue()+3*i+1].add(k.shiftRight(23)).add(k.shiftLeft(41)).mod(MOD);
			k = k.and(lmask);
			s0 = s0.xor(k.shiftLeft(8)).mod(MOD);
			s0 = s0.and(lmask);
			BigInteger test = k.shiftRight(GAP.intValue()).and(BigInteger.valueOf(~255));
			s0 = s0.subtract(test).mod(MOD);
			s0 = s0.and(lmask);
			s0 = s0.subtract(s0.shiftLeft(LBH.intValue())).mod(MOD);
			s0 = s0.and(lmask);
			t = spice[(i ^ 1)].xor(PI19.add(BigInteger.valueOf(blocksize)).mod(MOD));
			s0 = s0.add(t.shiftLeft(3)).mod(MOD);
			s0 = s0.and(lmask);
			s0 = s0.xor(t.shiftRight(GAP.intValue()+2));
			s0 = s0.and(lmask);
			s0 = s0.subtract(t).mod(MOD);
			s0 = s0.and(lmask);
			s0 = s0.xor(s0.shiftRight(LBQ.intValue()));
			s0 = s0.and(lmask);
			int and = s0.and(BigInteger.valueOf(15)).intValue();
			s0 = s0.add(permb[and]).mod(MOD);
			s0 = s0.and(lmask);
			t = spice[(i^2)];
			s0 = s0.xor(t.shiftRight(GAP.intValue()+4));
			s0 = s0.and(lmask);
			s0 = s0.add(s0.shiftLeft(LBT.intValue() + s0.and(BigInteger.valueOf(15)).intValue())).mod(MOD);
			s0 = s0.and(lmask);
			s0 = s0.add(t).mod(MOD);
			s0 = s0.and(lmask);
			s0 = s0.xor(s0.shiftRight(LBH.intValue()));
			s0 = s0.and(lmask);
		}
		return s0;
	}
	
	public static long decryptHPCShort(BigInteger s0, BigInteger[] KX)
	{
		int blocksize = 64;
		
		// Several shift sizes are calculated:
		BigInteger LBH = BigInteger.valueOf((blocksize + 1) / 2); //division rounds down
		BigInteger LBQ = (LBH.add(BigInteger.ONE)).divide(BigInteger.valueOf(2));
		BigInteger LBT = (LBQ.add(BigInteger.valueOf(blocksize))).divide(BigInteger.valueOf(4)).add(BigInteger.valueOf(2));
		BigInteger GAP = BigInteger.valueOf(64 - blocksize);
		
		for(int i = 7; i >= 0; i--)
		{
			s0 = s0.xor(s0.shiftRight(LBH.intValue()));
			BigInteger t = spice[(i^2)];
			s0 = s0.subtract(t).mod(MOD);
			
			// Inverse of s0 = s0.add(s0.shiftLeft(LBT.intValue() + s0.and(BigInteger.valueOf(15)).intValue())).mod(MOD);
			t = LBT.add(s0.and(BigInteger.valueOf(15)));
			s0 = s0.subtract(s0.subtract(s0.shiftLeft(t.intValue())).shiftLeft(t.intValue())).mod(MOD);
			
			t = spice[(i^2)];
			s0 = s0.xor(t.shiftRight(GAP.intValue()+4));
			
			int and = 13;//s0.and(BigInteger.valueOf(15)).intValue();
			s0 = s0.subtract(permbi[and]).mod(MOD);
			
			//Inverse of s0 = s0.xor(s0.shiftRight(LBQ.intValue())); is this:
			s0 = s0.xor(s0.shiftRight(LBQ.intValue()));
			s0 = s0.xor(s0.shiftRight(LBQ.multiply(BigInteger.valueOf(2)).intValue()));
			
			t = spice[(i ^ 1)].xor(PI19.add(BigInteger.valueOf(blocksize)).mod(MOD));
			s0 = s0.add(t).mod(MOD);
			s0 = s0.xor(t.shiftRight(GAP.intValue()+2));
			s0 = s0.subtract(t.shiftLeft(3)).mod(MOD);
			s0 = s0.add(s0.shiftLeft(LBH.intValue())).mod(MOD);
			t = s0.and(BigInteger.valueOf(255));
			BigInteger k = KX[t.intValue()];
			k = k.xor(spice[(i ^ 4)]);
			k = KX[t.intValue()+3*i+1].add(k.shiftRight(23)).add(k.shiftLeft(41)).mod(MOD);
			BigInteger test = k.shiftRight(GAP.intValue()).and(BigInteger.valueOf(~255));
			s0 = s0.add(test).mod(MOD);
			s0 = s0.xor(k.shiftLeft(8)).mod(MOD);
			s0 = s0.xor(s0.shiftRight(LBH.intValue()));;
			s0 = s0.subtract(t.shiftRight(13));
			s0 = s0.add(t.shiftRight(GAP.intValue()+i));
			t = spice[i^7];
			s0 = s0.xor(t);
			s0 = s0.subtract(s0.shiftLeft(LBH.intValue() + i)).mod(MOD);
			k = KX[s0.and(BigInteger.valueOf(255)).intValue()].add(spice[i]);
			s0 = s0.xor((k.shiftRight(GAP.intValue())).and(BigInteger.valueOf(~255)));
			s0 = s0.subtract(k.shiftLeft(8)).mod(MOD);
			//s0.and(BigInteger.valueOf(0xffffffffffffffffl));
			//BigInteger k = KX[s0.and(BigInteger.valueOf(255)).intValue()]; //+ spice[i]
		}
		return s0.longValue();
	}
	
	
	/**
	 * helper method to rotate BigInt values left. Cuts off leading zeros
	 * 
	 * @param value
	 * @param         shift: number of bits to shift. Must be positive.
	 * @param bitSize
	 * @return
	 */
	public static BigInteger shiftLeft(BigInteger value, int shift, int bitSize)
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
		BigInteger rotated = shiftLeft(new BigInteger("14142135623730950488"), 10, bitSize);
		System.out.println(rotated.toString(2));
		
		//testing encryption
		BigInteger[] kx = createKeyExpansionTable(2, 64, 0);
		BigInteger encrypted = HPCShort(1234567890l, kx);
		System.out.print("Encrypted value: " + encrypted.toString());
		Long decrypted = decryptHPCShort(encrypted, kx);
		System.out.println("Decrypted value: " + decrypted);	
	}
}
