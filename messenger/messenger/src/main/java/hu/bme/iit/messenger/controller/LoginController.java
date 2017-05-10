package hu.bme.iit.messenger.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.iit.messenger.controller.services.UserService;
import hu.bme.iit.messenger.model.User;

@RestController
public class LoginController {
	
	private static final String logoutPage = "/logout";
	private static final String loginPage = "/login";
	private static final String userSessionAttrib = "loggedInUser";
	@Autowired
	UserService userService;

	
	@RequestMapping(value=loginPage, method = RequestMethod.GET)
	public String showLoginForm(){
		return "login";		
	}
	
	@RequestMapping(value=loginPage, method = RequestMethod.POST)
	public String verifyLogin(	@RequestParam String email, @RequestParam String password, HttpSession session){		
		
		User user = userService.loginUser(email, password);
		
		if(user == null)
			return "login";		
		
		session.setAttribute(userSessionAttrib, user);
				
		return "redirect:/messenger";
	}
	
	@RequestMapping(value=logoutPage, method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(userSessionAttrib);
		
		return "login";
	}
}
