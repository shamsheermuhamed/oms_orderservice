package com.shopping.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.exception.RecordNotFoundException;
import com.shopping.model.ERole;
import com.shopping.model.Role;
import com.shopping.model.User;
import com.shopping.repository.RoleRepository;
import com.shopping.repository.UserRepository;
import com.shopping.request.JwtResponse;
import com.shopping.request.LoginRequest;
import com.shopping.request.MessageResponse;
import com.shopping.request.SignupRequest;
import com.shopping.security.JwtUtils;
import com.shopping.security.UserDetailsImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getFirstName(),signUpRequest.getLastName(),signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),signUpRequest.getPhone());

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error- Role is not found"));
					roles.add(adminRole);

					break;
				case "user":
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error-- Role is not found"));
					roles.add(userRole);

					break;
				default:
					throw new RecordNotFoundException();
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PutMapping("/forgot")
	public ResponseEntity<Object> forgotPassword(@RequestBody LoginRequest loginRequest){
		if (userRepository.existsByUsername(loginRequest.getUsername())) {
			User user= userRepository.findByUsername(loginRequest.getUsername()).get();
			user.setPassword(encoder.encode(loginRequest.getPassword()));
			userRepository.save(user);
			return ResponseEntity.ok(new MessageResponse("Password updated successfully!"));
		}
		else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: No user found with the given user Name!"));
		}	
	}
	
}