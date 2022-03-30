package com.cognizant.pensionmanagementportal.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Nayan,Akshita,Akhil
 * Entity/model class for PensionIDetail
 * Use of Lombok for default constructor,parameterized constructor and getters and setters
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class PensionDetail {

	/**
	 * instance variables for Pension Detail entity class
	 */

	/** Pensioner Name */
	private String name;
	
	/** Pensioner Date of Birth*/
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dateOfBirth;
	
	/** Pensioner Pan Card*/
	private String pan;
	
	/** Self or Family pension Type*/
	private String selfOrFamilyPension;
	
	/** pension Amount */
	private double pensionAmount;

}
