package com.cognizant.pensionmanagementportal.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Nayan,Akshita,Akhil
 * Entity/model class for PensionDisbursement ProcessPensionInput
 * USe of Lombok for default constructor,parameterized constructor and getters and setters
 *
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessPensionInput {
	/**
	 * instance variables for ProcessPensionInput entity class
	 */

	/**  Aadhaar Number*/
	@Id
	private Long aadharNumber;

	/** Pension Amount*/
	private Double pensionAmount;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessPensionInput other = (ProcessPensionInput) obj;
		if (pensionAmount == null) {
			if (other.pensionAmount != null)
				return false;
		} else if (!pensionAmount.equals(other.pensionAmount))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pensionAmount == null) ? 0 : pensionAmount.hashCode());
		return result;
	}
	
	
}
