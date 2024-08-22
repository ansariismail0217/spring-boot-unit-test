package com.example.gradlegroovydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gradlegroovydemo.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	boolean existsByEmail(String email);
	boolean existsByMobNum(Long mobNum);
	User findByName(String name);
	User findByEmail(String email);
	User findByMobNum(Long MobNum);

}
