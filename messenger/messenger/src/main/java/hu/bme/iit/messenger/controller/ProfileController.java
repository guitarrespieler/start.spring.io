package hu.bme.iit.messenger.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.iit.messenger.controller.services.UserService;
import hu.bme.iit.messenger.controller.services.UserService.ContactState;
import hu.bme.iit.messenger.model.User;

@RestController
public class ProfileController {
	
	@Autowired
	private UserService userService;

	public static final String myprofilePage = "/myprofile";
	public static final String otherusersProfile = "/profile";
	
	@RequestMapping(value = otherusersProfile, method = RequestMethod.GET)
	public String getOtherUsersProfile(@RequestParam("userid") Long userid, HttpSession session){
		if(!LoginController.isLoggedIn(session))
			return "{\"url\":\""+LoginController.loginPage+"\"}";
		try {
			User loggedInUser = (User) session.getAttribute(LoginController.userSessionAttribName);
			
			User user = userService.getUser(userid);
							
			if(user == null || loggedInUser == null) throw new NullPointerException();
			
			ContactState contactstate = userService.areFriends(user, loggedInUser);
			
			return createJsonfromUser(user, contactstate);

		} catch (Exception e) {
			return "{\"error\":\"Something went wrong. Try again later.\"}";
		}
	}
	@RequestMapping(value = myprofilePage, method = RequestMethod.GET)
	public String getLoggedInUsersProfile(HttpSession session){
		if(!LoginController.isLoggedIn(session))
			return "{\"url\":\""+LoginController.loginPage+"\"}";
		
		try{
			User loggedInUser = (User) session.getAttribute(LoginController.userSessionAttribName);
			
			return createJsonfromUser(loggedInUser, ContactState.SAME_USER);
		}catch (Exception e) {
			return "{\"error\":\"Something went wrong. Try again later.\"}";
		}
	}
	
	private String createJsonfromUser(User user, ContactState contactstate){
		StringBuilder sb = new StringBuilder();

		sb.append("{");
		
		sb.append("\"userid\":");
		sb.append("\""+ user.getId() + "\",");
		
		sb.append("\"firstname\":");
		sb.append("\""+ user.getFirstName() + "\",");
		
		sb.append("\"lastname\":");
		sb.append("\""+ user.getLastName() + "\",");
		
		sb.append("\"city\":");
		sb.append("\""+ user.getCity() + "\",");
		
		sb.append("\"birthdate\":");
		sb.append("\""+ user.getBirthDate() + "\",");

		sb.append("\"contactstate\":");
		sb.append("\"" + contactstate.name() + "\"");			
		
		sb.append("}");
		
		return sb.toString();
	}
}
