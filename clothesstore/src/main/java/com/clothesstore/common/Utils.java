package com.clothesstore.common;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	private final Random RANDOM = new SecureRandom();
	private final String NUMBERS = "0123456789";
	private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public String generateProducId(int length) {
		return generateRandomString(length);
	}
	
	public String generateCartId(int length) {
		return generateRandomString(length);
	}

	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length - 1; i++) {
			returnValue.append(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));
		}

		returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));

		return new String(returnValue);
	}
}
