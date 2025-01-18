package com.cts.Hotel_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Hotel_Management.dto.AuthenticationResponse;
import com.cts.Hotel_Management.dto.LoginRequest;
import com.cts.Hotel_Management.dto.UserDTO;
import com.cts.Hotel_Management.entity.User;
import com.cts.Hotel_Management.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(
			@RequestBody @Valid UserDTO userDTO) {
		return ResponseEntity.ok(userService.register(userDTO));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(
			@RequestBody @Valid LoginRequest loginRequest) {
		return ResponseEntity.ok(userService.login(loginRequest));
	}

}

