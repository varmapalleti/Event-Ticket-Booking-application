package com.collabs.events_booking.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String path = request.getRequestURI();
		if(path.equals("/api/auth/login")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	return;
        }

            String email = jwtUtil.extractEmail(token);
            String role = jwtUtil.extractRole(token);
            
           List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
           
           SimpleGrantedAuthority sg = new SimpleGrantedAuthority("ROLE_"+role);
           authorities.add(sg);
           System.out.println("Email: " + email);
           System.out.println("Role from token: " + role);
           System.out.println("Authorities: " + authorities);
           
           UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email,null,authorities);           
           SecurityContextHolder.getContext().setAuthentication(auth);
          
           filterChain.doFilter(request, response);
        }

		

}
