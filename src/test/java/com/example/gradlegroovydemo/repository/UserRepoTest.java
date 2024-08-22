package com.example.gradlegroovydemo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.gradlegroovydemo.model.User;

@ExtendWith(MockitoExtension.class)
public class UserRepoTest {

	@Mock
	private UserRepo repo;
	private User user1;
	private User user2;
	private List<User> users;

	@BeforeEach
	public void setUp() {
//		Simulate the behavior of the save() method
//      Assume the repository sets the id to 1L upon saving
		user1 = new User("Ismail", 7905111710L, "ismail@gmail.com", "Bangalore");
		user1.setId(1);
		user2 = new User("Ansari", 7905111711L, "ansari@gmail.com", "Hyderabad");
		user2.setId(2);
		users = Arrays.asList(user1, user2);
	}

	@AfterEach
	public void tearDown() {
		repo.deleteAll();
		user1 = null;
		user2 = null;
	}

	@Test
	public void whenSaveUser_thenUserIsSaved() {
//		Mock the save() method to return the savedUser when any User instance is passed
		when(repo.save(any(User.class))).thenReturn(user1);

//		Call the mocked repository's save method
		User result = repo.save(user1);
		System.out.println(result);

//		Assert that the result matches the expected outcome
		assertThat(result.getId()).isEqualTo(user1.getId());
		assertThat(result.getName()).isEqualTo(user1.getName());

	}

	@Test
	public void whenFindById_thenUserReturned() {
//		Mock the findById() method to return the user when id is queried
		when(repo.findById(1)).thenReturn(Optional.of(user1));

//		Call the mocked repository's findById() method
		Optional<User> result = repo.findById(1);

//		 Assert that the result matches the expected outcome
		assertThat(result).isPresent();
		assertEquals(result.get().getId(), 1);
		assertEquals(result.get().getName(), "Ismail");
	}

	@Test
	public void whenFindAll_thenListOfUsersReturned() {
//		Mock the findAll() method to return the predefined list of users
		when(repo.findAll()).thenReturn(users);

//		Call the mocked repository's findAll method
		List<User> result = repo.findAll();

//		Assert that the result matches the expected outcome
		assertThat(result).hasSize(2);
		assertEquals(result.get(0).getName(), "Ismail");
		assertEquals(result.get(1).getName(), "Ansari");

	}

	@Test
	public void whenUpdateUserById_thenUserIsUpdated() {
		String newName = "Updated Name";
//		Mocking findById to return the existing user
		when(repo.findById(1)).thenReturn(Optional.of(user1));

//		Assuming the save method updates the user and returns the updated user
//		when(repo.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

//      When
		Optional<User> foundUserOptional = repo.findById(1);
		foundUserOptional.ifPresent(user -> {
			user.setName(newName);
			repo.save(user);
		});

//      Then
		assertThat(foundUserOptional).isPresent();
		assertEquals(foundUserOptional.get().getName(), newName);
//      verify(repo,times(1)).save(any());
		verify(repo).save(any(User.class)); // Verify save was called with updated user
	}

	@Test
	public void whenDeleteById_thenUserIsDeleted() {
		
		when(repo.findById(1)).thenReturn(Optional.of(user1));
		System.out.println(repo.findById(1));
		
		repo.deleteById(1);
		verify(repo).deleteById(1);
		
	}

}
