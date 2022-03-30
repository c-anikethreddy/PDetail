package com.cognizant.pensionmanagementportal.controller;


import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.cognizant.pensionmanagementportal.exchangeserviceproxy.AuthorizationFeign;
import com.cognizant.pensionmanagementportal.exchangeserviceproxy.ProcessPensionFeign;
import com.cognizant.pensionmanagementportal.model.JwtRequest;
import com.cognizant.pensionmanagementportal.model.PensionerInput;
import com.cognizant.pensionmanagementportal.model.ProcessPensionInput;
import com.cognizant.pensionmanagementportal.service.PensionManagementPortalServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.FeignException;

import lombok.extern.slf4j.Slf4j;

/**
 *  
 * @author Neelima, Ramya, Aniketh, Satya
 * 
 * PensionManagementController is controller class to check for url mappings in this micro service
 * Annotated with @Controller to handle methods based on the @RequestMapping annotation
 */
@Slf4j 
@Controller 
public class PensionManagementController {
 
	/** 
	 * processPensionFeign reference of ProcessPensionFeign is autowired as feign client
	 */
	@Autowired
	private ProcessPensionFeign processPensionFeign;
	
	/**
	 * authFeign reference of AuthorizationFeign client interface is autowired
	 */
	@Autowired
	AuthorizationFeign authFeign;


	/** Http Session variable */
	@SuppressWarnings("unused")
	private HttpSession session;
    /**
     * to hold session of user
     */
	 String sessionHolder;
	
	/** Form parameter name to display error messages */
	private final static String messg = "msg";
	
	/** Service Layer call */
	@Autowired
	private PensionManagementPortalServiceImpl pensionManagementPortalServiceImpl;


	/** 
	 * This method loads when / is passed in request url
	 * This is the access page to portal ,
	 * it will allow authentic user to login into the application form this page
	 * @param msg ,model
	 * @return login jsp page
	 * 
	 */
	//	http://localhost:9595/pms/
	@RequestMapping("/")
	public String index(@RequestParam(defaultValue = "") final String msg, final Model model) {
		model.addAttribute(messg, msg);
		return "login";
	}

	/**
	 * This method provides login to webpage when user name and password is provided
	 * 
	 *  @param userId,
	 *  @param password, 
	 *  @param request
	 *  @param model, 
	 *  @return pensionerInput jsp page
	 */
	@RequestMapping("/pensiondetail")
	public String login(@RequestParam("username") String userId, @RequestParam("password") String password,
			HttpServletRequest request, ModelMap model) {
		log.info("START :: Method :: login() :: ");
		session = request.getSession();
		log.info(userId);
		log.info(password);
		JwtRequest jwtRequest = new JwtRequest();
		jwtRequest.setUsername(userId);
		jwtRequest.setPassword(password);
		String token = "Bearer ";
		try {
			
			token += authFeign.getToken(jwtRequest);
			//
			log.debug(token);
		} catch (Exception ex) {
			//System.out.println(ex);
			return "redirect:/?msg=Invalid Credentials";
		}
		//System.out.println(token);
		session.setAttribute("userId", userId);
		session.setAttribute("token", token);
		log.info(token);
		System.out.println(new PensionerInput());
		model.addAttribute("pensionInput", new PensionerInput());
		
		log.info("END :: Method :: login() :: ");
		return "pensionerInput";
	}
	
	
	/** This method takes pensionInput which has details like name, dateOfBirth, pan number, aadhaar number
	 * and pension type as input and return pension detail along with calculated pension amount.
	 * @param  pensionInput
	 * @param result for error
	 * @param model to map attributes
	 * @return string 
	 */
	@RequestMapping("/pensionerdetail")
	@HystrixCommand(fallbackMethod = "fallBackpensionDetail")
	public String pensionDetail(@ModelAttribute("pensionInput") final PensionerInput pensionInput,
			final BindingResult result, final ModelMap model) {
		log.debug("type: " + pensionInput.getPensionType());
		ResponseEntity<String> resultString;
		log.debug("string: " + pensionInput.toString());
		System.out.println(pensionInput);
		try {
			System.out.println(session.getAttribute("token"));
			resultString = processPensionFeign.getPensionDetail((String) session.getAttribute("token"), pensionInput);
			System.out.println("hi");
			System.out.println(resultString);
			if (resultString.getStatusCode() == HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS) {
				model.put(messg, "Please Login First");
				return "login";
			}

		} catch (FeignException.BadRequest e) {
			model.put("fillmsg", "Please Enter Correct Details");
			return "pensionerInput";
		}

		log.debug("string: " + resultString);
		Map<String, String> resultMap = pensionManagementPortalServiceImpl.resultTokenGenrationMethod(resultString,
				pensionInput.getAadharNumber());

		model.put("resultMap", resultMap);

		return "pensionerInput";
	}
	
	/**
	 * fallback method
	 * 
	 * @param pensionInput, @param result, @param model
	 * @return
	 */
	public String fallBackpensionDetail(@ModelAttribute("pensionInput") final PensionerInput pensionInput,
			final BindingResult result, final ModelMap model) {
		model.put(messg, "Service Down. Please Try Again Later!!!");
		return "failure";
	}

	/**
	 * On calculating pension amount successfully and allowing pension to disburse,
	 *  this method loads the disburse pension jsp page
	 * @param model
	 * @return dispersePension jsp page
	 */
	@RequestMapping("/pensiondisburse")
	public String pensionDisbure(final ModelMap model) {
		return "disbursePension";
	}

	/**
	 * pensionDetailMessage() takes input as aadhaar number verify the details of aadhaar and pension amount 
	 * and return success or failure page depending on condition
	 * @param aadharNum
	 * @param pensionerInput
	 * @param model
	 * @return string
	 */
	@RequestMapping("/pensiondisbursed")
	@HystrixCommand(fallbackMethod = "fallBackpensionDetailMessage")
	public String pensionDetailMessage(@RequestParam("aadhar") final Long aadharNum,
			@ModelAttribute("pensionInput") final PensionerInput pensionerInput,
			final ModelMap model) {
		log.info("START :: Method :: pensionDetailMessage() :: ");

         log.info("aadhaar number"+aadharNum);
         
		/**
		 *  verifies that Aadhaar number is same what was entered in Pensioner Input page.
		 */
		Optional<ProcessPensionInput> processPensionInput = pensionManagementPortalServiceImpl
				.processErrorMessage(aadharNum);

		if (!processPensionInput.isPresent()) {
			
			model.put("fillmsg", "Please fetch the pensioner's details again!!!!");
			return "pensionerInput";
		}
        
		ResponseEntity<String> messageString = processPensionFeign
				.getProcessingCode((String) session.getAttribute("token"),processPensionInput.get());

		if (messageString.getStatusCode() == HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS) {
			model.put(messg, "Please Login First");
			return "login";
		}

		String body = pensionManagementPortalServiceImpl.removeDQuotes(messageString.getBody());
		if (body.equalsIgnoreCase("success")) {
			model.addAttribute(messg, "Congratulations "+" !!!!! ");
			model.addAttribute("info", "Your Pension is successfully disbursed in your account ");
			return "success";
		}
		log.info("END :: Method :: pensionDetailMessage() :: ");
		model.put(messg, body);
		return "failure";
	}

	/**
	 * fallback method
	 * 
	 * @param aadharNum, @param pensionerInput, @param model
	 * @return
	 */
	public String fallBackpensionDetailMessage(@ModelAttribute("aadhar") final Long aadharNum,
			@ModelAttribute("pensionInput") final PensionerInput pensionerInput, 
			final ModelMap model) {
		model.put(messg, "Pensioner Detail micro service down, Please try again later");
		return "failure";
	} 
  /**
   * to expire session of user and logout from portal
   * @param request
   * @return
   */
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		log.info("START :: Method :: logout() :: ");
		request.getSession().invalidate();
		request.getSession(false);
		log.info("END :: Method :: logout() :: ");
		return "login";
	}


}
