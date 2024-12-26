package com.cts.Hotel_Management.service;

import com.cts.Hotel_Management.dto.LoginRequest;
import com.cts.Hotel_Management.dto.UserDTO;
import com.cts.Hotel_Management.entity.User;

//@Service
public interface UserService {
	
	String registerUser(UserDTO userDTO);
	String registerAdmin(UserDTO userDTO);
	UserDTO convertToDto(User user);
	User convertToEntity(UserDTO userDTO);
	void loginUser(LoginRequest loginRequest);
}
