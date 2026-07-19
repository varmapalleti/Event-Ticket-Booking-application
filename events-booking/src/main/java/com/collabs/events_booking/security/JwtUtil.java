package com.collabs.events_booking.security;


import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private static final String SECRET = "thisisaeventticketbookingsystemusingspringbootandreact";
	
	 private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
	 
	 public String generateToken(String email, String role) {
		 return Jwts.builder()
				 .setSubject(email)
				 .claim("roles", role)
				 .setIssuedAt(new Date())
				 .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
				 .signWith(SignatureAlgorithm.HS256,SECRET)
				 .compact();
	 }
	 
	 public String extractEmail(String token) {
		 return Jwts.parser()
				 .setSigningKey(SECRET)
				 .parseClaimsJws(token)
				 .getBody()
				 .getSubject();
	 }
	 public String extractRole(String token) {
		 return Jwts.parser()
				 .setSigningKey(SECRET)
				 .parseClaimsJws(token)
				 .getBody()
				 .get("roles",String.class);
	 }
	 
	 public boolean validateToken(String token) {
		 try {
			 Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			 return true;
		 }catch(Exception e) {
			 return false;
		 }
	 }
	   
	   
}
