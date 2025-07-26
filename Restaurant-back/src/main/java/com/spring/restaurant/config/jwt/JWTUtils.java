package com.spring.restaurant.config.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JWTUtils
{
	private static final int DAYS = 15;
	private static final int HOURS_PER_DAY = 24;
	private static final int MINUTES_PER_HOUR = 60;
	private static final int SECONDS_PER_MINUTE = 60;
	private static final long JWT_TOKEN_VALIDITY_MILLISECONDS = DAYS * HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE * 1000;
	private static final String SECRET = "restaurantprojectrestaurantprojectrestaurantprojectrestaurantprojectrestaurantprojectrestaurantprojectrestaurantprojectrestaurantprojectrestaurantproject";

	public boolean invalidToken(String token)
	{
		if (token == null || token.isEmpty())
			return true;
		return isTokenExpired(token);
	}

	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token)
	{
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public Map<String, Object> generateToken(String userName)
	{
		String token = doGenerateToken(userName);
		Map<String, Object> tokenMap = new HashMap<>();
		tokenMap.put("access_token", token);
		tokenMap.put("expires_in", getExpirationDateFromToken(token));
		tokenMap.put("expires_in_milli_seconds", JWT_TOKEN_VALIDITY_MILLISECONDS);
		tokenMap.put("type", "Bearer");
		return tokenMap;
	}

	public static void main(String[] args)
	{
		JWTUtils utils = new JWTUtils();
		Map<String, Object> token = utils.generateToken("test");
		System.out.println(token);
		Object accessToken = token.get("access_token");
		String realToken = accessToken == null ? "" : accessToken.toString();
		System.out.println(utils.getUsernameFromToken(realToken));
		System.out.println(utils.getExpirationDateFromToken(realToken));
	}

	private String doGenerateToken(String subject)
	{
		long currentTimeMillis = System.currentTimeMillis();
		return Jwts.builder().setSubject(subject).setIssuedAt(new Date(currentTimeMillis))
				.setExpiration(new Date(currentTimeMillis + JWT_TOKEN_VALIDITY_MILLISECONDS)).signWith(SignatureAlgorithm.HS512, getSecret())
				.compact();
	}

	private static String getSecret()
	{
		return SECRET;
	}
}
