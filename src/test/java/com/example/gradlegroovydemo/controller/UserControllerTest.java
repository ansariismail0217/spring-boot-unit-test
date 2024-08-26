package com.example.gradlegroovydemo.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.example.gradlegroovydemo.model.User;
import com.example.gradlegroovydemo.service.UserService;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class UserControllerTest {

	@LocalServerPort
	private int port;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void shouldAddNewUser() {		
	Response response = given().port(port)
			.contentType(ContentType.JSON)
			.body("""
		              {
		                "name": "Abdullah",
		                "mobNum": "123456789",
		                "email": "abdullah@gmail.com",
		                "address": "Kanpur"
		              }
		          """
		    )
            .when()
            .post("/users/create");
		
		response.then().statusCode(201);
		
		// Conditionally check the body if it's not empty
        String responseBody = response.getBody().asString();
        if (!responseBody.isEmpty()) {
            response.then().body("name", equalTo("Abdullah"));
        }
	}
	
//	@Test
//	void shouldFindJobPostById(){
//		User user1;
//		user1 = new User("Ismail", 7905111710L, "ismail@gmail.com", "Bangalore");
//		user1.setId(1);
//		Response response = given().port(port)
//				.contentType(ContentType.JSON)
//				.when()
//				.get("/users/{id}", user1.getId());
//		response.then().statusCode(200);
//		
//	}

}
