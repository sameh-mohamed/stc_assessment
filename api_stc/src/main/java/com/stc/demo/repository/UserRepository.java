package com.stc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.demo.model.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserEmail(String userEmail);
	
}
