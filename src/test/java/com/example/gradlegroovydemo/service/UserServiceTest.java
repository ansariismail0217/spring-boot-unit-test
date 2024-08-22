package com.example.gradlegroovydemo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.gradlegroovydemo.model.User;
import com.example.gradlegroovydemo.repository.UserRepo;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepo userRepo;

	@InjectMocks
	private UserService userService;
	private User user1;
	private User user2;
	List<User> userList;

	@BeforeEach
	public void setUp() {
		userList = new ArrayList<>();
		user1 = new User("Ismail", 7905111710L, "ismail@gmail.com", "Bangalore");
		user2 = new User("Ansari", 7905111711L, "ansari@gmail.com", "Hyderabad");
		userList.add(user1);
		userList.add(user2);
	}

	@AfterEach
	public void tearDown() {
		user1 = null;
		user2 = null;
		userList = null;
	}
	
	@Test
	public void givenUserToAddShouldReturnAddedProduct(){
	     //stubbing
	     when(userRepo.save(any())).thenReturn(user1);
	     userService.createUser(user1);
	     verify(userRepo,times(1)).save(any());
	}

}
