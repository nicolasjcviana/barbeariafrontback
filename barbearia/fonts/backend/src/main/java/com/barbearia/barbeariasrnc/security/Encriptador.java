package com.barbearia.barbeariasrnc.security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encriptador {

	private static final String ALGO = "RSA";

	public static String encripta(String data) throws Exception {
		return Base64.getEncoder().encodeToString(data.getBytes());
	}

	public static String encripta(String encryptedData, PrivateKey privateKey) throws Exception {
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.PRIVATE_KEY, privateKey);
		byte[] decValue = c.doFinal(encryptedData.getBytes());
		return new String(decValue);
	}

	private static Key generateKey(byte[] keys) throws Exception {
		return new SecretKeySpec(keys, ALGO);
	}
	
}
