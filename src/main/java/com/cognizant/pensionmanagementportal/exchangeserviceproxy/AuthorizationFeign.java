package com.cognizant.pensionmanagementportal.exchangeserviceproxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.pensionmanagementportal.model.AuthResponse;
import com.cognizant.pensionmanagementportal.model.JwtRequest;

/**
 * @author Nayan, Akshita, Akhil
 * 
 * AuthorizationFeign is a feign Client interface to connect
 *  from authorization micro service 
 * annotation @FeignClient - passing name of microservice and its url
 *
 */
//@FeignClient(name = "authorization-service", url = "http://auth-lb-1427334456.us-east-1.elb.amazonaws.com")
//@FeignClient(name = "authorization-service", url = "http://localhost:9696/")
@FeignClient(name = "authorization-service", url = "http://authorizationservice-env.us-east-1.elasticbeanstalk.com")
public interface AuthorizationFeign {

	/**
	 * this method request for jwt token
	 * @param jwtRequest
	 * @return String
	 */
	@PostMapping("/auth/getToken")
	//@PostMapping("/getToken")
	public String getToken(@RequestBody JwtRequest jwtRequest);

	/**
	 * this method checks for token validity
	 * @param token
	 * @return response- boolean value
	 */
	@GetMapping("/auth/validate")
	//@GetMapping("/validate")
	public AuthResponse getValidity(@RequestHeader("Authorization") String token);

}