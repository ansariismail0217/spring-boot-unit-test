package com.example.gradlegroovydemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gradlegroovydemo.model.User;
import com.example.gradlegroovydemo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	CREATE
	public ResponseEntity<User> addUser(@RequestBody User user){
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}
	
//	READ ALL
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}
	
//	READ ONE
	public ResponseEntity<User> getUserById(@PathVariable int id){
		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}	
	
//	UPDATE
	public ResponseEntity<User> updateUserById(@PathVariable int id, @RequestBody User user){
		return new ResponseEntity<>(userService.updateById(user, id), HttpStatus.ACCEPTED);
	}
	
	
//	DELETE
	
	

}
