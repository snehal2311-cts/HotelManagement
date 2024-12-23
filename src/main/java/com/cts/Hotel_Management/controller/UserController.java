package com.cts.Hotel_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Hotel_Management.dto.LoginRequest;
import com.cts.Hotel_Management.dto.UserDTO;
import com.cts.Hotel_Management.entity.User;
import com.cts.Hotel_Management.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/register")
	public String  registerUser(@RequestBody @Valid UserDTO userDTO){
		
		 userService.registerUser(userDTO);
		 return "Registration is successful !";
	}
	@PostMapping("/admin/register")
	public ResponseEntity<String>  registerAdmin(@RequestBody @Valid UserDTO userDTO){
		
		 
		 return new ResponseEntity<>(userService.registerAdmin(userDTO),HttpStatus.CREATED);
	}
	
	@PostMapping("/user/login")
	public User LoginUser(@RequestBody @Valid LoginRequest loginRequest) {
		
		return userService.loginUser(loginRequest);
		
	}
	
	

}
