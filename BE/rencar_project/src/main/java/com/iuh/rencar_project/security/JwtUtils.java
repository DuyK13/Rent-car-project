package com.iuh.rencar_project.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Trần Thế Duy
 * @datetime May 1, 2021 3:11:07 PM
 * @version 0.1
 */

@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.jwtExpirationInMs}")
	private int jwtExporationMs;

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	private Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	private Boolean isTokenExpired(String token) {
		return getExpirationDateFromToken(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExporationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		String username = this.getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
