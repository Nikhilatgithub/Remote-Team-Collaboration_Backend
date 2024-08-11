package com.teamCollaboration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teamCollaboration.dto.AuthResponse;
import com.teamCollaboration.dto.LoginRequest;
import com.teamCollaboration.dto.RegisterRequest;
import com.teamCollaboration.entities.User;
import com.teamCollaboration.security.JwtTokenProvider;
import com.teamCollaboration.security.UserDetailsServiceImpl;
import com.teamCollaboration.services.UserService;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	private  AuthenticationManager authenticationManager;
	@Autowired
    private  JwtTokenProvider jwtTokenProvider;
	@Autowired
    private  UserDetailsServiceImpl userDetailsService;
	@Autowired
    private  PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
			Authentication authentication = authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			System.out.println("user authentication : "+authentication.isAuthenticated());
			
			 UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
				String token = jwtTokenProvider.generateToken(userDetails);
				System.out.println(userDetails);
				return ResponseEntity.ok(new AuthResponse(token));
		} catch (UsernameNotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
        
       
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User newUser = new User();
        newUser.setFirstname(request.getFirstname());
        newUser.setLastname(request.getLastname());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt password
        // Set roles if needed
        // newUser.setRoles(...);
        // Save user in database
        // userRepository.save(newUser);
        userService.createUser(newUser);

        return ResponseEntity.ok("User registered successfully");
    }
}
