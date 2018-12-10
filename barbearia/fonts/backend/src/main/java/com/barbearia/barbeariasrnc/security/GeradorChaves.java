package com.barbearia.barbeariasrnc.security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class GeradorChaves {

	private static final String ARQUIVO = "privatekey.txt";
	private KeyPairGenerator geradorChaves;
	private KeyPair parChaves;
	private PrivateKey chavePrivada;

	private static GeradorChaves instance;
	
	public static GeradorChaves getInstance() {
		if (instance == null) {
			try {
				instance = new GeradorChaves();
			} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	private GeradorChaves() throws NoSuchAlgorithmException, NoSuchProviderException {
		this.geradorChaves = KeyPairGenerator.getInstance("RSA");
		this.geradorChaves.initialize(512);
		try {
			criarChavePrivada();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void criarChavePrivada() throws Exception {
		Path path = Paths.get(ARQUIVO);
		
		if (path.toFile().exists()) {
			this.chavePrivada = getChavePrivada(ARQUIVO);
		} else {
			this.parChaves = this.geradorChaves.generateKeyPair();
			this.chavePrivada = parChaves.getPrivate();
			writeToFile(ARQUIVO, this.chavePrivada.getEncoded());
		}
		
	}

	private PrivateKey getChavePrivada(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}
	
	public PrivateKey getChavePrivada() {
		return chavePrivada;
	}

	public void setChavePrivada(PrivateKey chavePrivada) {
		this.chavePrivada = chavePrivada;
	}

	public PublicKey getChavePublica(PrivateKey chavePrivada) throws NoSuchAlgorithmException, InvalidKeySpecException {
		RSAPrivateCrtKey privk = (RSAPrivateCrtKey) chavePrivada;
		RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privk.getModulus(), privk.getPublicExponent());
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey myPublicKey = keyFactory.generatePublic(publicKeySpec);
		return myPublicKey;
	}

	private void writeToFile(String path, byte[] key) throws IOException {
		FileOutputStream fos = new FileOutputStream(Paths.get(path).toFile());
		fos.write(key);
		fos.flush();
		fos.close();
	}

	public PublicKey getChavePublica() {
		try {
			return getChavePublica(chavePrivada);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

}
