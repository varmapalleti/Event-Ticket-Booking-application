package com.collabs.events_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collabs.events_booking.enums.UserRole;
import com.collabs.events_booking.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByRole(UserRole role);
}
