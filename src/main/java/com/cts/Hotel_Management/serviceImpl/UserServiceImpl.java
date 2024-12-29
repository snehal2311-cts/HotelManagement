package com.cts.Hotel_Management.serviceImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.Hotel_Management.dto.AuthenticationResponse;
import com.cts.Hotel_Management.dto.LoginRequest;
import com.cts.Hotel_Management.dto.UserDTO;
import com.cts.Hotel_Management.entity.User;
import com.cts.Hotel_Management.repository.UserRepository;
import com.cts.Hotel_Management.service.JwtService;
import com.cts.Hotel_Management.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String register(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO cannot be null");
        }

        User user = convertToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
        try {
            user = userRepository.save(user);
        } catch (Exception e) {
            // Log the exception and rethrow or handle it as needed
            throw new RuntimeException("Error saving user", e);
        }

       // String token = jwtService.generateToken(Optional.of(user));

        return "Registered Successfully";
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials", e);
        }

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(Optional.of(user));
        return new AuthenticationResponse(token);

    }
    @Override
	public UserDTO convertToDto(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	@Override
	public User convertToEntity(UserDTO userDTO) {
		return modelMapper.map(userDTO, User.class);
	}
}

