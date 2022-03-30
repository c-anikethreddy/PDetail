package com.cognizant.pensionmanagementportal;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.pensionmanagementportal.exchangeserviceproxy.ProcessPensionFeign;
import com.cognizant.pensionmanagementportal.model.ProcessPensionInput;
import com.cognizant.pensionmanagementportal.repository.PensionManagementRepository;
import com.cognizant.pensionmanagementportal.service.PensionManagementPortalServiceImpl;
@SpringBootTest
public class PensionManagementPortalServiceTest {


	@InjectMocks
	PensionManagementPortalServiceImpl pensionManagementPortalServiceImpl;
	
	@Mock
	PensionManagementRepository pensionManagementRepository;
	
	@Mock
	ProcessPensionFeign processPensionFeign;
	ResponseEntity<String> resultString;
	Long aaddharNum;
	Map<String, String> resultMap = new HashMap<String, String>();
	Optional<ProcessPensionInput> processInput;

	@BeforeEach
	void init() {
		aaddharNum = Long.parseLong("123465789123");
		resultString = new ResponseEntity<String>("{\"name\":\"aakash\",\"type\":\"private\",\"pensionAmount\":25000.0}", HttpStatus.OK);
		resultMap = new HashMap<String, String>();	
		
	}
	
	@Test
	void resultTokenGenrationMethodTest() {
		resultMap.put("name","aakash");
		resultMap.put("pensionAmount","25000.0");
		resultMap.put("type","private");
		
		Assertions.assertEquals(resultMap, pensionManagementPortalServiceImpl.resultTokenGenrationMethod(resultString, aaddharNum));
	}
	

	@Test
	void processErrorMessageTest() {
		
		 processInput = Optional.ofNullable(new ProcessPensionInput());
		 when(pensionManagementRepository.findById(aaddharNum)).thenReturn(processInput);
		resultMap.put("name","aakash");
		resultMap.put("pensionAmount","25000.0");
		resultMap.put("type","private");
		Assertions.assertEquals(processInput, pensionManagementPortalServiceImpl.processErrorMessage(aaddharNum));
	}
	
	@Test
	void removeDQuotesTest() {
		Assertions.assertEquals("riya", pensionManagementPortalServiceImpl.removeDQuotes("\"riya\""));
	}
}
