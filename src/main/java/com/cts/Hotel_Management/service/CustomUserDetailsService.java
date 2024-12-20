package com.cts.Hotel_Management.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.Hotel_Management.entity.User;
import com.cts.Hotel_Management.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(email);
        		//.orElseThrow(() -> new OurException("Username/Email not Found"));
        
        if(user==null) {
        	throw new UsernameNotFoundException("No user found with this email"+email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),Collections.emptyList());
        
    }


}
