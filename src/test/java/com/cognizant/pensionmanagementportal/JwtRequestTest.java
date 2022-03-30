package com.cognizant.pensionmanagementportal;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.pensionmanagementportal.model.JwtRequest;

@SpringBootTest
public class JwtRequestTest {
	JwtRequest jwtRequest = new JwtRequest("pensioner1","pensioner1");

	@Test
	public void testNotNullJwtRequest() {
		assertNotNull(jwtRequest);
	}
	
	@Test
	public void testUsername() {
		jwtRequest.setUsername("pensioner");
		assertEquals(jwtRequest.getUsername(), "pensioner");
	}

	@Test
	public void testPassword() {
		jwtRequest.setPassword("pensioner");
		assertEquals(jwtRequest.getPassword(), "pensioner");
	}

}
