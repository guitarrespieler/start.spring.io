package hu.bme.iit.messenger.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class FriendListController {
	public static final String friendlistPage = "/friends";
	
	@RequestMapping(value = friendlistPage,method = RequestMethod.GET)
	public String getFriendList(HttpSession session){
		
		
		
		
		return null;
	}
}
