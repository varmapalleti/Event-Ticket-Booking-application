package com.collabs.events_booking.dto.requestdto;

import com.collabs.events_booking.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	
	private String email;
	private String password;
	private UserRole role;

}
