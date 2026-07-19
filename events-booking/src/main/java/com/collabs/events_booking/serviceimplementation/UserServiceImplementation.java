package com.collabs.events_booking.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.collabs.events_booking.dto.requestdto.UserRequestDto;
import com.collabs.events_booking.dto.responseDto.UserResponseDto;
import com.collabs.events_booking.enums.UserRole;
import com.collabs.events_booking.model.User;
import com.collabs.events_booking.repository.UserRepository;
import com.collabs.events_booking.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

	private final UserRepository userRepo;

	@Override
	public UserResponseDto addUser(UserRequestDto udto) {
		User u1 = User.builder().name(udto.getName()).email(udto.getEmail()).password(udto.getPassword())
				.phone(udto.getPhone()).role(udto.getRole()).build();
		User user = userRepo.save(u1);
		return mapToResponse(user);
	}

	private UserResponseDto mapToResponse(User user) {
		UserResponseDto urd = UserResponseDto.builder().id(user.getId()).email(user.getEmail()).name(user.getName())
				.phone(user.getPhone()).role(user.getRole()).build();
		return urd;
	}

	@Override
	public UserResponseDto getUser(Long id) {
		User ur = userRepo.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

		return mapToResponse(ur);
	}

	@Override
	public UserResponseDto updateuser(Long id, UserRequestDto udto) {
		User u1 = userRepo.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
		if (udto.getName() != null)
			u1.setName(udto.getName());
		if (udto.getEmail() != null)
			u1.setEmail(udto.getEmail());
		if (udto.getPassword() != null)
			u1.setPassword(udto.getPassword());
		if (udto.getPhone() != null)
			u1.setPhone(udto.getPhone());
		if (udto.getRole() != null)
			u1.setRole(udto.getRole());
		User user = userRepo.save(u1);
		return mapToResponse(user);
	}

	@Override
	public String deleteUser(Long id) {
		User ur = userRepo.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
		userRepo.delete(ur);
		return "user deleted";
	}

	@Override
	public List<UserResponseDto> getUserByRole(UserRole role) {
		List<User> ur = userRepo.findByRole(role);
		return ur.stream().map(this::mapToResponse).collect(Collectors.toList());
	}
}
