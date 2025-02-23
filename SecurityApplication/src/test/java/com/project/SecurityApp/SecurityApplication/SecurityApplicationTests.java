package com.project.SecurityApp.SecurityApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.SecurityApp.SecurityApplication.entities.User;
import com.project.SecurityApp.SecurityApplication.services.JwtService;

@SpringBootTest
class SecurityApplicationTests {
	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {

		User user = new User(4L, "vinay@gmail.com", "1234", "Anuj");
		String token = jwtService.generateAccessToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);
 
	}

}
