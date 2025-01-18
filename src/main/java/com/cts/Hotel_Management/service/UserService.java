package com.cts.Hotel_Management.service;

import com.cts.Hotel_Management.dto.AuthenticationResponse;
import com.cts.Hotel_Management.dto.LoginRequest;
import com.cts.Hotel_Management.dto.UserDTO;
import com.cts.Hotel_Management.entity.User;

import jakarta.validation.Valid;


public interface UserService {

	UserDTO register(@Valid UserDTO userDTO);

	AuthenticationResponse login( @Valid LoginRequest loginRequest);

	 User convertToEntity(UserDTO userDTO) ;
	 UserDTO convertToDto(User user) ;
}
