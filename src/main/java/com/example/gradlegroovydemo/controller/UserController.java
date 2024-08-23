package com.example.gradlegroovydemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gradlegroovydemo.model.User;
import com.example.gradlegroovydemo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	CREATE
	@PostMapping("create")
	public ResponseEntity<User> addUser(@RequestBody User user){
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}
	
//	READ ALL
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}
	
//	READ ONE
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id){
		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}	
	
//	UPDATE
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable int id, @RequestBody User user){
		return new ResponseEntity<>(userService.updateById(user, id), HttpStatus.ACCEPTED);
	}	
	
//	DELETE
	public ResponseEntity<?> deleteUserById(@PathVariable int id){
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	

}
