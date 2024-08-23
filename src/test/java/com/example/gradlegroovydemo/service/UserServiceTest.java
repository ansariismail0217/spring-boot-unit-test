package com.example.gradlegroovydemo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		user1.setId(1);
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
	public void whenSaveUser_thenUserIsSaved(){
	     //stubbing
	     when(userRepo.save(any())).thenReturn(user1);
	     userService.createUser(user1);
	     verify(userRepo,times(1)).save(any());
	}
	
	@Test
	public void whenGetById_thenUserIsReturned() {
//		Mock the findById() method to return the user when id is queried
		when(userRepo.findById(1)).thenReturn(Optional.of(user1));
		
//		Call the mocked service getById method
		User result = userService.getById(1);
		
//		Assert that the result matches the expected outcome
		assertEquals(1, result.getId());;
	}
	
	@Test
	public void whenGetAll_thenReturnAllUsers() {
//		Mock the findAll() method to return the predefined list of users
		when(userRepo.findAll()).thenReturn(userList);
		
//		Call the mocked service getAll() method
		List<User> result = userService.getAll();
		
//		Assert that the result matched the expected outcome
		assertThat(result).hasSize(2);
	}
	
	@Test
	public void whenUpdateById_thenUserIsUpdated() {
//		Mock the findById() method to return the existing user
		when(userRepo.findById(1)).thenReturn(Optional.of(user1));
		
//		Updating the name of the existing user
		Optional<User> foundUserOptional = userRepo.findById(1);
		foundUserOptional.ifPresent(user -> {
			user.setName("Updated Name");
			userService.createUser(user);
		});
		
		assertThat(foundUserOptional).isPresent();
		assertEquals(foundUserOptional.get().getName(), "Updated Name");
		verify(userRepo).save(any(User.class));
	}
	
	@Test
	public void whenDeleteById_thenUserIsDeleted() {
		when(userRepo.findById(1)).thenReturn(Optional.of(user1));

		userService.deleteById(user1.getId());
		verify(userRepo).deleteById(1);
		
		
	}

}
