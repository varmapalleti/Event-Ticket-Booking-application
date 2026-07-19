package com.collabs.events_booking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collabs.events_booking.dto.requestdto.AuthRequest;
import com.collabs.events_booking.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody AuthRequest ar){
		System.out.println("Login method called");
		return new ResponseEntity<String>(userService.login(ar),HttpStatus.OK);
	}
}
