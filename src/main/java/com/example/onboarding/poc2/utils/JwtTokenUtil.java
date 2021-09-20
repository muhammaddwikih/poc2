package com.example.onboarding.poc2.utils;

import java.io.File;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

	public String getUserId(String token) {
		Claims claims = parseToken(token).getBody();
		return claims.getSubject();
	}

	public String getUsername(String token) {
		Claims claims = parseToken(token).getBody();
		return claims.getSubject();
	}

	public String getNickname(String token) {
		Claims claims = parseToken(token).getBody();
		return (String) claims.get("nickname");
	}
	
	public String getNrp(String token) {
		Claims claims = parseToken(token).getBody();
		return (String) claims.get("nrp");
	}
	
	public String getDivisi(String token) {
		Claims claims = parseToken(token).getBody();
		return (String) claims.get("divisi");
	}

	public Date getExpirationDate(String token) {
		Claims claims = parseToken(token).getBody();
		return claims.getExpiration();
	}

	public Collection<? extends GrantedAuthority> getAuthorities(String token) {
		Claims claims = parseToken(token).getBody();

		List<GrantedAuthority> roles = ((List<String>) claims.get("roles"))
				.stream()
				.map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return roles;
	}

	public boolean validate(String token) {
		try {
			parseToken(token);
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return false;
	}

	private Key getKey() {
		try {
			File file = new ClassPathResource("public.der").getFile();
			Key publicKey = KeyReader.readPublicKey(file);
			return publicKey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Jws<Claims> parseToken(String token) {
		return Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
	}

}