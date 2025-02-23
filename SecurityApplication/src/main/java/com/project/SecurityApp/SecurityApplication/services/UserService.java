package com.project.SecurityApp.SecurityApplication.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.SecurityApp.SecurityApplication.dto.LoginDto;
import com.project.SecurityApp.SecurityApplication.dto.SignUpDto;
import com.project.SecurityApp.SecurityApplication.dto.UserDto;
import com.project.SecurityApp.SecurityApplication.entities.User;
import com.project.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.project.SecurityApp.SecurityApplication.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

	private final UserRepository  userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	//private final AuthenticationManager authenticationManager;
	//private final JwtService jwtService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(username).orElseThrow(()-> new BadCredentialsException("User with Email "+username+" not found "));
	}
	
	
	 public User getUserById(Long userId) {
	        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id "+ userId +
	                " not found"));
	    }
	 
	 public User getUsrByEmail(String email) {
	        return userRepository.findByEmail(email).orElse(null);
	    }
	public UserDto signUp(SignUpDto signUpDto) {
		 Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
	        if(user.isPresent()) {
	            throw new BadCredentialsException("User with email already exits "+ signUpDto.getEmail());
	        }

	        User toBeCreatedUser = modelMapper.map(signUpDto, User.class);
	        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

	        User savedUser = userRepository.save(toBeCreatedUser);
	        return modelMapper.map(savedUser, UserDto.class);
	}
	
	 public User save(User newUser) {
	        return userRepository.save(newUser);
	    }
}
