package com.cts.Hotel_Management.service;

import com.cts.Hotel_Management.dto.LoginRequest;
import com.cts.Hotel_Management.dto.UserDTO;
import com.cts.Hotel_Management.entity.User;

//@Service
public interface UserService {
	
	User registerUser(UserDTO userDTO);
	User loginUser(LoginRequest loginRequest);
}
