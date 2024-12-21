package com.cts.Hotel_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Hotel_Management.dto.LoginRequest;
import com.cts.Hotel_Management.dto.UserDTO;
import com.cts.Hotel_Management.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users/register")
	public String  registerUser(@RequestBody UserDTO userDTO){
		
		 userService.registerUser(userDTO);
		 return "Registration is successful !";
	}
	
	@PostMapping("/users/login")
	public String LoginUser(@RequestBody LoginRequest loginRequest) {
		userService.loginUser(loginRequest);
		return "Login is successfully !";
		
	}
	
	

}
