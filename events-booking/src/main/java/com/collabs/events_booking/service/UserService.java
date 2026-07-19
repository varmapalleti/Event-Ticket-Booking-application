package com.collabs.events_booking.service;

import java.util.List;
import java.util.Optional;

import com.collabs.events_booking.dto.requestdto.AuthRequest;
import com.collabs.events_booking.dto.requestdto.UserRequestDto;
import com.collabs.events_booking.dto.responseDto.UserResponseDto;
import com.collabs.events_booking.enums.UserRole;

public interface UserService {
    UserResponseDto addUser(UserRequestDto udto);
    UserResponseDto getUser(Long id);
    List<UserResponseDto> getUserByRole(UserRole role);

    UserResponseDto updateuser(Long id, UserRequestDto udto);
    String deleteUser(Long id);
    String login(AuthRequest ar);
    
}
