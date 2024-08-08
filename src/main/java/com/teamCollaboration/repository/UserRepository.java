package com.teamCollaboration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teamCollaboration.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.email = :email")
	public User getUserByEmail(@Param("email") String email);
}
