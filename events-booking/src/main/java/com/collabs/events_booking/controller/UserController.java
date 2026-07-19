package com.collabs.events_booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.collabs.events_booking.dto.requestdto.UserRequestDto;
import com.collabs.events_booking.dto.responseDto.UserResponseDto;
import com.collabs.events_booking.enums.UserRole;
import com.collabs.events_booking.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<UserResponseDto> insertUser( @Valid @RequestBody UserRequestDto udto){
		return ResponseEntity.status(201).body(userService.addUser(udto));
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<UserResponseDto> fetchUser(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUser(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> removeUser(@RequestParam Long id){
		return ResponseEntity.ok(userService.deleteUser(id));
	}
	
	@GetMapping("/getbyrole/{role}")
	public ResponseEntity<List<UserResponseDto>> fetchUserByRole(@RequestParam UserRole role){
		return ResponseEntity.ok(userService.getUserByRole(role));
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<UserResponseDto> updateUserData(@PathVariable Long id, 
														@Valid @RequestBody UserRequestDto udto){
		return ResponseEntity.ok(userService.updateuser(id, udto));
	}
}











