package hu.bme.iit.messenger.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import hu.bme.iit.messenger.controller.services.UserService;
import hu.bme.iit.messenger.model.User;

public class ProfileController {
	
	@Autowired
	private UserService userService;

	public static final String profilePage = "/profile";
	
	public String getProfile(@RequestParam("userid") Long userid, HttpSession session){
		if(!LoginController.isLoggedIn(session))
			return "{\"url\":\""+LoginController.loginPage+"\"}";
		try {
			User user = userService.getUser(userid);
			
			return createJsonfromUser(user);
		} catch (Exception e) {
			return "{\"error\":\"Something went wrong. Try again later.\"}";
		}
	}
	
	private String createJsonfromUser(User user){
		StringBuilder sb = new StringBuilder();

		sb.append("{");
		
		sb.append("\"userid\":");
		sb.append("\" "+ user.getId() + "\",");
		
		sb.append("\"firstname\":");
		sb.append("\" "+ user.getFirstName() + "\",");
		
		sb.append("\"lastname\":");
		sb.append("\" "+ user.getLastName() + "\",");
		
		sb.append("\"city\":");
		sb.append("\" "+ user.getCity() + "\",");
		
		sb.append("\"birthdate\":");
		sb.append("\" "+ user.getBirthDate() + "\"");
		
		sb.append("}");
		
		return sb.toString();
	}
}
