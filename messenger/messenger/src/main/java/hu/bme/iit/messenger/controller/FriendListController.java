package hu.bme.iit.messenger.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.iit.messenger.model.User;

@RestController
public class FriendListController {
	public static final String friendlistPage = "/friends";
	
	@RequestMapping(value = friendlistPage,method = RequestMethod.GET)
	public String getFriendList(HttpSession session){
		if(!LoginController.isLoggedIn(session))
			return "{\"url\":\""+LoginController.loginPage+"\"}";
		try {
			User loggedinuser = (User) session.getAttribute(LoginController.userSessionAttribName);
			
			return createFriendList(loggedinuser.getFriends());	
		} catch (Exception e) {
			return "{\"error\": \"Something went wrong. Try again later.\"}";
		}
	}
	
	private String createFriendList(List<User> friends) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"friends\":[");
		
		if(friends != null)
			if(!friends.isEmpty()){
				for (int i = 0; i < friends.size(); i++) {
					User user = friends.get(i);
					
					String link = ProfileController.otherusersProfile + "?userid=" + user.getId();
					
					sb.append(getUserInJson(link, user.getFirstName() + " " + user.getLastName(), user.getCity()));
					if(i < friends.size() - 1)
						sb.append(",");
				}
		}		

		sb.append("]}");
		return sb.toString();
	}
	
	private String getUserInJson(String link, String title, String subtitle){
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append("\"link\":");
		sb.append("\"" + link + "\",");
		sb.append("\"title\":");
		sb.append("\"" + title + "\",");
		sb.append("\"subtitle\":");
		sb.append("\"" + subtitle + "\"");
		sb.append("}");
		return sb.toString();
	}
	
}
