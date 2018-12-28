package test.java.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

import com.javawebapp.service.HastyPuddingCipherService;

public class TestHastyPuddingCipherService
{
	@Test
	public void testShiftLeft()
	{
		BigInteger val = new BigInteger("14142135623730950488");
		BigInteger rotated = HastyPuddingCipherService.shiftLeft(new BigInteger("14142135623730950488"), 10, 64);
		assertTrue(rotated.toString(2).equals("101111010101101011111010011110000101110001010110001100010001"));
	}
	
	@Test
	public void testEncryptDecrypt()
	{
		long beforeEncrypt = 1234567890l;
		BigInteger[] kx = HastyPuddingCipherService.createKeyExpansionTable(2, 64);
		BigInteger encrypted = HastyPuddingCipherService.encryptHPCShort(beforeEncrypt, kx);
		assertFalse(beforeEncrypt == encrypted.longValue());
		Long decrypted = HastyPuddingCipherService.decryptHPCShort(encrypted, kx);
		assertTrue(beforeEncrypt == decrypted);
	}
}
