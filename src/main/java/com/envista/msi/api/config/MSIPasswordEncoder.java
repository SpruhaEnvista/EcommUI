package com.envista.msi.api.config;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author SANKER
 *
 */
public class MSIPasswordEncoder implements PasswordEncoder {

	private final Logger log = LoggerFactory.getLogger(MSIPasswordEncoder.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.crypto.password.PasswordEncoder#encode(java.
	 * lang.CharSequence)
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		String hashed = null;
		if (rawPassword != null) {
			hashed = rawPassword.toString();
		}
		return hashed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.crypto.password.PasswordEncoder#matches(java
	 * .lang.CharSequence, java.lang.String)
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword == null || encodedPassword.length() == 0) {
			log.warn("Empty encoded password");
			return false;
		}
		return true;
	}

	/**
	 * @param plaintext
	 * @param hashed
	 * @return
	 */
	private boolean checkpw(CharSequence plaintext, String hashed) {
		if (plaintext == null) {
			log.warn("Empty raw password");
			return false;
		}
		return hashed.equals(plaintext.toString());
	}

	/**
	 * @param plaintext
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String plaintext) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(plaintext.getBytes("UTF-8"));
		byte raw[] = md.digest();
		return (new sun.misc.BASE64Encoder()).encode(raw);
	}
	
}
