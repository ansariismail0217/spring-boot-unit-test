package com.example.gradlegroovydemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gradlegroovydemo.exception.NoSuchUserExistsException;
import com.example.gradlegroovydemo.exception.UserAlreadyExistsException;
import com.example.gradlegroovydemo.model.User;
import com.example.gradlegroovydemo.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
//	 CREATE
	public User createUser(@RequestBody User user) {
//		check if customer already exist
		if (userRepo.existsByMobNum(user.getMobNum())) {
			throw new UserAlreadyExistsException(
					"Customer already exists with the mobile number- " + user.getMobNum());
		}

		else if (userRepo.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException("User already exists with email- " + user.getEmail());
		}

		else {
			return userRepo.save(user);
		}
	}

//	READ ALL
	public List<User> getAll() {
		return userRepo.findAll();
	}
	
//	READ ONE
	public User getById(@PathVariable Integer id) {
		return userRepo.findById(id)
				.orElseThrow(() -> new NoSuchUserExistsException("No user present with id = " + id));
	}
	
//	UPDATE
	public User updateById(User user, Integer id) {
		if (userRepo.existsById(id)) {
			User existingUser = userRepo.findById(id).get();
			existingUser.setName(user.getName());
			existingUser.setMobNum(user.getMobNum());
			existingUser.setEmail(user.getEmail());
			existingUser.setAddress(user.getAddress());
			return userRepo.save(existingUser);
//			return ResponseEntity.ok(existingUser);
		} else {
			throw new NoSuchUserExistsException("No such user exists!");
		}
	}
	
//	DELETE
//	public ResponseEntity<Map<String, Boolean>> deleteById(Integer id) {
//		if (userRepo.existsById(id)) {
//			userRepo.deleteById(id);
//			Map<String, Boolean> response = new HashMap<>();
//			response.put("deleted", Boolean.TRUE);
//			return ResponseEntity.ok(response);		
//		}
//
//		else {
//			throw new NoSuchUserExistsException("No user exist with id = "+id);
//		}
//	}
	public void deleteById(int id) {
		User existingUser = userRepo.findById(id)
				.orElseThrow(()-> new NoSuchUserExistsException("No user exist with id = "+id));
		userRepo.deleteById(existingUser.getId());
	}

}
