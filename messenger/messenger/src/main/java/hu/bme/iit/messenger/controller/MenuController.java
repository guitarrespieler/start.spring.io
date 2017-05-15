package hu.bme.iit.messenger.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class MenuController {
	public static final String getMenuElements = "/getmenuitems";
	
	public static final String mainTitle = "\"IIT Messenger\"";
	
	public static final String MessagesMenu = "{\"name\": \"Messages\", \"link\": \""+ ConversationsController.conversationsPage +"\"}";
	public static final String ProfileMenu = "{\"name\": \"My profile\", \"link\": \""+ ProfileController.myprofilePage +"\"}";
	public static final String FriendListMenu = "{\"name\": \"Friends\", \"link\": \""+ FriendListController.friendlistPage +"\"}";
	
	public static final String menuList = "{\"title\":" + mainTitle + ","
										+ "\"menuitems\": [" + MessagesMenu + "," +ProfileMenu + "," + FriendListMenu + "]}";
	
	
	@RequestMapping(value=getMenuElements, method = RequestMethod.GET)
	public String getMenuElements(HttpSession session){
		if(!LoginController.isLoggedIn(session))
			return "{\"url\": \"/login\"}";

		return menuList;
	}

}
