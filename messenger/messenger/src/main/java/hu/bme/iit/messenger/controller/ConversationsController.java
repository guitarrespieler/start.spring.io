package hu.bme.iit.messenger.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.iit.messenger.controller.services.UserService;
import hu.bme.iit.messenger.model.User;

@RestController
public class ConversationsController {
	
	@Autowired
	private UserService userService;
	
	public static final String conversationsPage = "/conversations";
	public static final String convlist = "/getConversations";

	
	@RequestMapping(value = convlist, method=RequestMethod.GET)
	public String getConversationsList(HttpSession session){
		if(!LoginController.isLoggedIn(session))
			return "redirect: /login";
		try{
			User user = (User)session.getAttribute(LoginController.userSessionAttribName);
		}catch (Exception e) {
			return "{\"error\": \"Something went wrong. Try again later.\"}";
		}
		
		return "conversations";
	}
	
}
