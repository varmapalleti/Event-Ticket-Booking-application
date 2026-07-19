package com.collabs.events_booking.dto.responseDto;

import com.collabs.events_booking.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
	private Long id;
	private String name;
	private String email;
	private Long phone;
	private UserRole role;
}
