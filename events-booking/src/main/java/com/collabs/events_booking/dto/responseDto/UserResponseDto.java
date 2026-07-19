package com.collabs.events_booking.responseDto;

import com.collabs.events_booking.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
	private Integer id;
	private String name;
	private String email;
	private Long phone;
	private UserRole role;
}
