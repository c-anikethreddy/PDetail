package com.cognizant.pensionmanagementportal.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Nayan, Akshita ,Akhil
 * 
 *Entity/model class for JwtResponse returns jwt token for that username 
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse implements Serializable {
	/**
	 * instance variables for JwtResponse entity class
	 */
	private String username;
	private String jwtToken;

}

