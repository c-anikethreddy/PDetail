package com.cognizant.pensionmanagementportal.service;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.cognizant.pensionmanagementportal.model.ProcessPensionInput;
import com.cognizant.pensionmanagementportal.repository.PensionManagementRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Nayan, Akshita, Akhil
 * PensionManagementPortalServiceImpl is the implementation class for service interface
 * It is service class that has the implementation for several business methods
 * Annotated with @Service -provide some business functionalities
 * Annotated with @Slf4j -for logging messages
 */
@Slf4j
@Service
public class PensionManagementPortalServiceImpl implements PensionManagementPortalService {

	/** 
	 * pensionManagementRepository reference of PensionManagementRepository is autowired 
	 */
	@Autowired
	PensionManagementRepository pensionManagementRepository;



	/**
	 * This method takes resultString as input and using StringTokenizer generate tokens 
	 * which then passed in substring method to generate key and value 
	 * @param resultString
	 * @param aaddharNum
	 * @return Map<String, String>
	 */
	@Override
	public Map<String, String> resultTokenGenrationMethod(final ResponseEntity<String> resultString,
			final Long aaddharNum) {
		log.info("START :: Method :: resultTokenGenrationMethod() :: ");
		Map<String, String> resultMap = new HashMap<String, String>();
		String resultStringBody = resultString.getBody();
		resultStringBody = resultStringBody.substring(1, resultStringBody.length() - 1);
		log.info(resultStringBody);
		List<String> listToken = Collections.list(new StringTokenizer(resultStringBody, ",")).stream()
				.map(token -> (String) token).collect(Collectors.toList());
		for (String temp : listToken) {
			String[] keyvalue = temp.split(":");
			keyvalue[0] = keyvalue[0].substring(1, keyvalue[0].length() - 1);

			if (keyvalue[1].charAt(0) == '"') {
				keyvalue[1] = keyvalue[1].substring(1, keyvalue[1].length() - 1);
			}
			resultMap.put(keyvalue[0], keyvalue[1]);
		}
		log.info(""+resultMap);

		Double pensionAmount = Double.parseDouble(resultMap.get("pensionAmount"));
		ProcessPensionInput processPensionInput = new ProcessPensionInput(aaddharNum, pensionAmount);
		pensionManagementRepository.save(processPensionInput);
		log.info("END :: Method :: resultTokenGenrationMethod() :: ");
		return resultMap;
	}

	/**
	 * This method returns pensionInput type entity on basis of aadhaar 
	 * @param aadharNum
	 * @return ProcessPensionInput
	 */
	@Override
	public Optional<ProcessPensionInput> processErrorMessage(final Long aadharNum) {
		
		return pensionManagementRepository.findById(aadharNum);
	}


	/**
	 * This method removes double quotes from string passed in the parameter
	 * @param string
	 * @return string
	 */
	@Override
	public String removeDQuotes(final String string) {
		return string.substring(1, string.length() - 1);
	}

}
