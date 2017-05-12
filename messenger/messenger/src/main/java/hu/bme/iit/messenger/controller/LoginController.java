package hu.bme.iit.messenger.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.iit.messenger.controller.services.UserService;
import hu.bme.iit.messenger.model.User;

@RestController
public class LoginController {
	
	public static final String loginPage = "/login";
	public static final String logoutPage = "/logout";
	public static final String loginUser = "/loginUser";
	public static final String conversationsPage = "/conversations";
	private static final String userSessionAttribName = "loggedInUser";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value=loginUser, method = RequestMethod.POST)
	public String verifyLogin(@RequestBody User reqUser, HttpSession session){
		try{
			userService.checkEmailFormat(reqUser.getEmail());			
			User user = userService.loginUser(reqUser.getEmail(), reqUser.getPassword());
			
			session.setAttribute(userSessionAttribName, user);
			
			return "{\"url\": \"" + conversationsPage +"\"}";			
		}catch (NullPointerException e) {
			return "{\"error\": \"" +e.getMessage() +"\"}";
		}catch (IllegalArgumentException e) {
			return "{\"error\": \"" +e.getMessage() +"\"}";
		}catch (Exception e) {
			return "{\"error\": \"Something went wrong. Try again later.\"}";
		}			
	}
	
	@RequestMapping(value=logoutPage, method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(userSessionAttribName);
		
		return "redirect:/";
	}
}
