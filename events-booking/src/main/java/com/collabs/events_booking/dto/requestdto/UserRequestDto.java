package com.collabs.events_booking.dto.requestdto;
import org.hibernate.validator.constraints.Length;

import com.collabs.events_booking.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDto {
	@NotBlank(message = "name should not be null")
	private String name;
	@NotBlank(message = "email should not be null")
	@Email(message = "enter valid email")
	private String email;
	@Length(min = 3, max = 10)
	private String password;
	private Long phone;
	private UserRole role;
}
