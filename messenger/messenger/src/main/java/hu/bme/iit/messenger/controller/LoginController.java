package hu.bme.iit.messenger.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.iit.messenger.controller.services.UserService;
import hu.bme.iit.messenger.model.User;

@RestController
public class LoginController {
	
	private static final String logoutPage = "/logout";
	private static final String loginUser = "/loginUser";
	private static final String userSessionAttrib = "loggedInUser";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value=loginUser, method = RequestMethod.POST)
	public String verifyLogin(@RequestBody User user, HttpSession session){		
		
		String email = user.getEmail();
		
//		if(user == null)
	//		return "redirect:/";		
		
		session.setAttribute(userSessionAttrib, email);
				
		return "redirect:/conversations";
	}
	
	@RequestMapping(value=logoutPage, method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(userSessionAttrib);
		
		return "login";
	}
}
