package hu.bme.iit.messenger.controller;

import java.util.Calendar;

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
	
	public static final String messengerPage = "/messenger.html";
	
	public static final String userSessionAttribName = "loggedInUser";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value=loginUser, method = RequestMethod.POST)
	public String verifyLogin(@RequestBody User reqUser, HttpSession session){
		
		createTestUsers();
		
		try{
			userService.checkEmailFormat(reqUser.getEmail());			
			User user = userService.loginUser(reqUser.getEmail(), reqUser.getPassword());
			
			session.setAttribute(userSessionAttribName, user);
			
			return "{\"url\": \"" + messengerPage +"\"}";			
		}catch (NullPointerException e) {
			return "{\"error\": \"" +e.getMessage() +"\"}";
		}catch (IllegalArgumentException e) {
			return "{\"error\": \"" +e.getMessage() +"\"}";
		}catch (Exception e) {
			return "{\"error\": \"Something went wrong. Try again later.\"}";
		}			
	}

	private void createTestUsers() {
		User userx = new User();
		
		userx.setEmail("tibor.zsiga@hotmail.com");
		userx.setPassword("asdf");
		userx.setBirthDate(Calendar.getInstance().getTime());
		userx.setCity("Dunakeszi");
		userx.setFirstName("Tibor");
		userx.setLastName("Zsiga");
		userService.addUser(userx);
		
		User user2 = new User();
		
		user2.setEmail("test@iit.bme.hu");
		user2.setPassword("asdf");
		user2.setBirthDate(Calendar.getInstance().getTime());
		user2.setCity("Pécs");
		user2.setFirstName("János");
		user2.setLastName("Kovács");
		userService.addUser(user2);
		
		
		User user3 = new User();
		
		user3.setEmail("test2@iit.bme.hu");
		user3.setPassword("asdf");
		user3.setBirthDate(Calendar.getInstance().getTime());
		user3.setCity("Debrecen");
		user3.setFirstName("István");
		user3.setLastName("Szabó");
		userService.addUser(user3);
		
		userService.createFriendship(userx, user2);	
		userService.createFriendship(userx, user3);	
	}
	
	@RequestMapping(value=logoutPage, method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(userSessionAttribName);
		
		return "{\"url\": \"/login.html\"}";
		
	}
	
	/**
	 * @return true if a user is logged in
	 */
	public static boolean isLoggedIn(HttpSession session){
		if(session.getAttribute(userSessionAttribName) == null)
			return false;
		return true;
	}
}
