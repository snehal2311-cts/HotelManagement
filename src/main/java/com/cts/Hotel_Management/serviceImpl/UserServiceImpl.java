package com.cts.Hotel_Management.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.Hotel_Management.dto.LoginRequest;
import com.cts.Hotel_Management.dto.UserDTO;
import com.cts.Hotel_Management.entity.User;
import com.cts.Hotel_Management.repository.UserRepository;
import com.cts.Hotel_Management.service.UserService;


@Service
public class UserServiceImpl implements UserService  {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User registerUser(UserDTO userDTO) {
		
		User user=new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole(userDTO.getRole());
		return userRepository.save(user);
	}
	
	@Override
	public User loginUser(LoginRequest loginRequest) {
		User user=userRepository.findByEmail(loginRequest.getEmail());
//		if(user==null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//			throw new RuntimeException("Invalid username or password");
//		}
		return user;
	}
}
