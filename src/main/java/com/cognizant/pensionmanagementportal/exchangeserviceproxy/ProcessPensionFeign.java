package com.cognizant.pensionmanagementportal.exchangeserviceproxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.pensionmanagementportal.model.PensionerInput;
import com.cognizant.pensionmanagementportal.model.ProcessPensionInput;

/**
 * @author Nayan, Akshita, Akhil
 * 
 * ProcessPensionFeign is a feign Client interface to fetch details
 *  from PensionProcess micro service 
 * annotation @FeignClient - passing name of microservice and its url
 *
 */
@FeignClient(name = "Pension-Process",url = "http://pps-env.us-east-1.elasticbeanstalk.com")
//@FeignClient(name = "Pension-Process",url = "http://localhost:9393")
public interface ProcessPensionFeign {
	
	/**
	 * getPensionDetail() gets the pension detail of pensioner by giving pensionerInput as parameter
	 * @param pensionerInput
	 * @return pension details 
	 */
	@PostMapping("/pps/pensiondetail")
	//@PostMapping("/pensiondetail")
	ResponseEntity<String> getPensionDetail(@RequestHeader(name = "Authorization") String token,
			@RequestBody PensionerInput pensionerInput);

	/** 
	 * getProcessingCode() gets the status code 
	 * It verifies pensioner details and disburse pension
	 * @param processPensionInput
	 * @return process status code 
	 */
	@PostMapping("/pps/processpension")
	//@PostMapping("/processpension")
	ResponseEntity<String> getProcessingCode(@RequestHeader(name = "Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput);
}
