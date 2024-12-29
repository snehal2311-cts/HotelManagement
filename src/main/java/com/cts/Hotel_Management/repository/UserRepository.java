package com.cts.Hotel_Management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.Hotel_Management.dto.UserDTO;
import com.cts.Hotel_Management.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	Optional<User> findByEmail(String email);
}
