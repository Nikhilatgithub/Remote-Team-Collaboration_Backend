package com.teamCollaboration.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teamCollaboration.dto.PasswordResetRequest;
import com.teamCollaboration.entities.Employee;
import com.teamCollaboration.entities.User;
import com.teamCollaboration.repository.EmployeeRepository;
import com.teamCollaboration.repository.UserRepository;
import com.teamCollaboration.services.OTPService;

@CrossOrigin
@RestController
public class PasswordResetController {

	@Autowired
	private OTPService otpService;

	@Autowired
	private EmployeeRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

//    @PostMapping("/forgot-password")
//    public ResponseEntity<?> forgotPassword(@RequestParam ("email") String email) {
//    	
//    	System.out.println(email);
//        User user = userRepository.findByUsername(email);
//        if (user != null) {
//            String otp = otpService.generateOTP(email);
//            otpService.sendOTPEmail(email, otp);
//            return ResponseEntity.ok("OTP sent to your email.");
//        }
//        return ResponseEntity.status(404).body("Email not found.");
//    }

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> requestBody) {
		String email = requestBody.get("email");
		System.out.println(email);
		Employee user = userRepository.getUserByEmail(email);
		if (user != null) {
			String otp = otpService.generateOTP(email);
			otpService.sendOTPEmail(email, otp);
			return ResponseEntity.ok("OTP sent to your email.");
		}
		return ResponseEntity.status(404).body("Email not found.");
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest request) {
	    String email = request.getEmail();
	    String otp = request.getOtp();
	    String newPassword = request.getNewPassword();

	    if (otpService.validateOTP(email, otp)) {
	        Employee user = userRepository.getUserByEmail(email);
	        if (user != null) {
	           
	            
	            
	            user.setPassword(passwordEncoder.encode(newPassword));
	            userRepository.save(user);
	            otpService.clearOTP(email);
	            return ResponseEntity.ok("Password reset successfully.");
	        } else {
	            return ResponseEntity.status(404).body("User not found.");
	        }
	    }
	    return ResponseEntity.status(400).body("Invalid OTP.");
	}


}
