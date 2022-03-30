package com.cognizant.pensionmanagementportal.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cognizant.pensionmanagementportal.model.ProcessPensionInput;

/**
 * @author Nayan, Akshita, Akhil
 * 
 * PensionManagementPortalService is service interface for web portal
 * It has methods to provide business requirements 
 * 
 */
public interface PensionManagementPortalService {
	/**
	 * resultTokenGenrationMethod returns tokenised parameters 
	 * when string type response entity is passed as an argument
	 * @param resultString
	 * @param aaddharNum
	 * @return Map
	  */
	Map<String, String> resultTokenGenrationMethod(ResponseEntity<String> resultString, Long aaddharNum);

	/**
	 *  process error message takes adhaar as input 
	 *  @param aadharNum
	 *  @return ProcessPensionInput
	 */
	Optional<ProcessPensionInput> processErrorMessage(Long aadharNum);
	

	/** removes Double Quotes
	 * @param string
	 * @return String without ""
	 */
	String removeDQuotes(String string);

	
}
