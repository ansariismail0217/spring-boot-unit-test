package com.example.gradlegroovydemo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.gradlegroovydemo.model.User;
import com.example.gradlegroovydemo.service.UserService;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class UserControllerTest {

	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
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
	public void whenSaveUserById_thenUserIsSaved() throws Exception{
//		when(userService.createUser(user1)).thenReturn(user1);
		
		String mockUserJson =
				"{	 \n" +
                "    \"name\": \"Abdullah\",\n" +
                "    \"mobNum\": 123456789,\n" +
                "    \"email\": \"abdullah@gmail.com\",\n" +
                "    \"address\": \"Bangalore\",\n" +
                "}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockUserJson)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
				
	}

}
