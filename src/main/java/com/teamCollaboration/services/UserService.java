package com.teamCollaboration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamCollaboration.entities.User;
import com.teamCollaboration.repository.UserRepository;

@Service
public class UserService {

	 @Autowired
	 private  UserRepository userRepository;
	 
	 public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    public Optional<User> getUserById(Long id) {
	        return userRepository.findById(id);
	    }

	    public User createUser(User user) {
	        // Perform validation or business logic if needed
	        return userRepository.save(user);
	    }

	    public User updateUser(Long id, User user) {
	        if (userRepository.existsById(id)) {
	            user.setId(id); // Ensure the ID is set for update operation
	            return userRepository.save(user);
	        } else {
	            throw new RuntimeException("User not found with id: " + id);
	        }
	    }

	    public void deleteUser(Long id) {
	        userRepository.deleteById(id);
	    }
}
