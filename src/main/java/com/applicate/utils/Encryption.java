package com.applicate.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;

@Component
public class Encryption {

	private static final String ENC_DEC_KEY = "Applicate";

	private StandardPBEStringEncryptor standardEncryptor = new StandardPBEStringEncryptor();

	public Encryption() {
        standardEncryptor.setPassword(ENC_DEC_KEY);
	}

	public String encrypt(String value) {
      return standardEncryptor.encrypt(value);
	}

	public String decrypt(String value) {
      return standardEncryptor.decrypt(value);
	}
	
}
