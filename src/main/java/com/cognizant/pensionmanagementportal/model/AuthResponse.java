package com.cognizant.pensionmanagementportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Nayan,Akshita,Akhil
 * Entity/model class for AuthResponse
 * Variable to check token validity
 * Use of lombok for getters, setters and constructors 
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponse {
	/**
	 * instance variable for entity class
	 */
	private boolean isValid;
}


