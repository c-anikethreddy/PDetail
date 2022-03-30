package com.cognizant.pensionmanagementportal;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.pensionmanagementportal.model.JwtResponse;

@SpringBootTest
public class JwtResponseTest {
	

	@Test
	public void testNotNullJwtResponse() {
		JwtResponse jwtResponse = new JwtResponse("pensioner@mail.com","abc");
		assertNotNull(jwtResponse);
	}

	@Test
	public void testGetToken() {
		JwtResponse jwtResponse = new JwtResponse("pensioner@mail.com","abc");
		assertEquals(jwtResponse.getJwtToken(), "abc");
	}

	@Test
	public void testPassword() {
		JwtResponse jwtResponse = new JwtResponse("pensioner@mail.com","abc");
		assertEquals(jwtResponse.getUsername(), "pensioner@mail.com");
	}

}
