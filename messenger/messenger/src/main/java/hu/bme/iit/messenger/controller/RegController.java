package hu.bme.iit.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.iit.messenger.controller.services.UserService;
import hu.bme.iit.messenger.model.User;

@RestController
public class RegController {
	
	@Autowired
	UserService service;
	
	public static final String regPage = "/registernewuser";
	
	
	
	@RequestMapping(value=regPage, method = RequestMethod.POST)
	public String registerUser(@RequestBody User newUser){
		try{
			service.checkParams(newUser);
			if(service.getUserByEmail(newUser.getEmail()) != null)	throw new IllegalArgumentException("Email already in use.");
			
			service.addUser(newUser);
			return "{\"url\": \"" + LoginController.loginPage + "\"}";
		}catch (NullPointerException e) {
			return "{\"error\": \"" +e.getMessage() +"\"}";
		}catch (IllegalArgumentException e) {
			return "{\"error\": \"" +e.getMessage() +"\"}";
		}catch (Exception e) {
			return "{\"error\": \"Something went wrong. Try again later.\"}";
		}
		
		
	}

}
