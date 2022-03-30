package com.cognizant.pensionmanagementportal.model;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Nayan,Akshita,Akhil
 * Entity/model class for PensionerInput
 * USe of Lombok for default constructor,parameterized constructor and getters and setters
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class PensionerInput {

	/**
	 * instance variables for Pensioner Input entity class
	 */
	
	/** Pensioner Name */
	@NotNull(message = "{userName.message}")
	@Size(min = 3, max = 30, message = "{userName.message}")
	private String name;

	/** Pensioner DOB */
	@NotNull(message = "{userDateOfBirth.message}")
	// @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
	private Date dateOfBirth;

	/** Pensioner PAN */
	@Size(min = 10, max = 10, message = "{pan.message}")
	private String pan;

	/** Pensioner Aadhaar Number */
	private long aadharNumber;

	/** pension Type */
	private String pensionType;
}
