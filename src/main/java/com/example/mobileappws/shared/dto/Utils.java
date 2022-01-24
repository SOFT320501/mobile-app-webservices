package com.example.mobileappws.shared.dto;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.mobileappws.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class Utils {

	private final Random RANDOM= new SecureRandom();
	private final String ALPHABET="0123456789ABCDEFGHUJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final int ITERATIONS=10000;
	private final int KEY_LENGTH = 256;
	
	public String generateUserId(int length)
	{
		return generateRandomString(length);
	}
	
	public String generateAddressId(int length)
	{
		return generateRandomString(length);
	}
	
	
	private String generateRandomString(int length)
	{
		StringBuilder returnValue=new StringBuilder(length);
		for(int i=0;i<length;i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}
	
	public static boolean hasTokenExpired(String token) {
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.getTokenSecret())
				.parseClaimsJws(token).getBody();
		Date tokenExpirationDate=claims.getExpiration();
		Date todayDate=new Date();
		
		return tokenExpirationDate.before(todayDate);
	}
	
	
}
