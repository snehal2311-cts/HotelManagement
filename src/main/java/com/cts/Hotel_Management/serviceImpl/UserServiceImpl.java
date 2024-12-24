package com.cts.Hotel_Management.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.Hotel_Management.dto.LoginRequest;
import com.cts.Hotel_Management.dto.UserDTO;
import com.cts.Hotel_Management.entity.Role;
import com.cts.Hotel_Management.entity.User;
import com.cts.Hotel_Management.repository.RoleRepository;
import com.cts.Hotel_Management.repository.UserRepository;
import com.cts.Hotel_Management.service.UserService;


@Service
public class UserServiceImpl implements UserService  {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User registerUser(UserDTO userDTO) {
		
//		User user=new User();
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setPhoneNumber(userDTO.getPhoneNumber());
//		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
////		user.setRole(userDTO.getRole());
//		return userRepository.save(user);
////		return null;
		User user=new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//		System.out.println("------got user ------");
		Set<Role> roles = new HashSet<>();
		Role adminRole = roleRepository.findByRole("ROLE_USER").get();
		roles.add(adminRole);
		user.setRole(roles);
		
		return  userRepository.save(user);
		
	}
	
	@Override
	public User loginUser(LoginRequest loginRequest) {
		User user=userRepository.findByEmail(loginRequest.getEmail()).get();
		if(user==null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		}
		return user;
	}

	@Override
	public String registerAdmin(UserDTO userDTO) {
		User user=new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//		System.out.println("------got user ------");
		Set<Role> roles = new HashSet<>();
		Role adminRole = roleRepository.findByRole("ROLE_ADMIN").get();
		roles.add(adminRole);
		user.setRole(roles);
		
		userRepository.save(user);
		
//		System.out.println("-------------got roles----------");
		
		return "Admin registration successsful";
	}
}
