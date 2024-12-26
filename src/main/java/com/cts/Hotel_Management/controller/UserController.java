package com.cts.Hotel_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Hotel_Management.dto.LoginRequest;
import com.cts.Hotel_Management.dto.UserDTO;
import com.cts.Hotel_Management.entity.User;
import com.cts.Hotel_Management.service.UserService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api")
@Validated
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public String registerUser(@RequestBody @Valid UserDTO userDTO) {
        logger.info("User registration request received for user: {}", userDTO.getEmail());
       
        logger.info("User registered successfully: {}", userDTO.getEmail());
        return  userService.registerUser(userDTO);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<String> registerAdmin(@RequestBody @Valid UserDTO userDTO) {
        logger.info("Admin registration request received for user: {}", userDTO.getEmail());
        String response = userService.registerAdmin(userDTO);
        logger.info("Admin registered successfully: {}", userDTO.getEmail());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public String loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        logger.info("User login request received for username: {}", loginRequest.getEmail());
         userService.loginUser(loginRequest);
        logger.info("User logged in successfully: {}", loginRequest.getEmail());
        return "Login is successfull";
    }
	
	

}
