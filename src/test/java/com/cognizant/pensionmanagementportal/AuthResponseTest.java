package com.cognizant.pensionmanagementportal;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.pensionmanagementportal.model.AuthResponse;

@SpringBootTest
public class AuthResponseTest {
	AuthResponse authResponse = new AuthResponse(true);

	@Test
	public void testNotNullAuthResponse() {
		Assertions.assertNotNull(authResponse);
	}
	
	@Test
	public void testIsValid() {
		authResponse.setValid(true);
		assertEquals(authResponse.isValid(), true);
	}

	
}
