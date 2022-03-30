package com.cognizant.pensionmanagementportal.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Nayan,Akshita,Akhil
 * JwtRequest entity class takes request of username and password
 * Use of Lombok for default constructor,parameterized constructor and getters and setters
 */

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtRequest implements Serializable{

	/**
	 * username for user to login
	 */
	private String username="root@gmail.com";
	/**
	 * password for login
	 */
	private String password="root";
	
	
}
