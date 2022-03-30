package com.cognizant.pensionmanagementportal;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 
 * @author Neelima, Ramya, Aniketh, Satya
 * Main class for Pension Management Portal Application
 * Annotated with @SpringBootApplication, @ComponentScan to scan all base packages
 * Annotated with @EnableFeignClients for creating REST API clients
 * Annotated with @EnableCircuitBreaker to set up a fallback in application logic
 *
 */
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableSwagger2
public class PensionManagementPortalApplication {


	/**
	 * Main function to run the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PensionManagementPortalApplication.class, args);
	}

	/**
	 * Swagger Docket
	 * @return
	 */
	@Bean
	public Docket swaggerConfiguration()
		{
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.cognizant.pensionmanagementportal"))
					.build()
					.apiInfo(apiDetails());
		
		}

	/**
	 * Swagger Api Info
	 * @return
	 */
	private ApiInfo apiDetails()
	{
		
		return new ApiInfo(
				"Pension Management Portal",
				"Microservice Form Pension Management Project",
				"1.0",
				"Free To Use",
				new springfox.documentation.service.Contact("Admin", "", "admin@cognizant.com"),
				"API Licesence",
				"....", Collections.emptyList());
		}
}
