package hu.bme.iit.messenger.controller;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.iit.messenger.controller.services.ConversationService;
import hu.bme.iit.messenger.controller.services.UserService;
import hu.bme.iit.messenger.model.Conversation;
import hu.bme.iit.messenger.model.User;

@RestController
public class ConversationsController {
	
	@Autowired
	private UserService userService;
	
	private ConversationService convService;
	
	public static final String conversationsPage = "/conversations";
	public static final String convlist = "/getConversations";
	public static final String conversation = "/conversation";

	
	@RequestMapping(value = convlist, method = RequestMethod.GET)
	public String getConversationsList(HttpSession session){
		if(!LoginController.isLoggedIn(session))
			return "{\"url\":\""+LoginController.loginPage+"\"}";
		try{
			User user = (User)session.getAttribute(LoginController.userSessionAttribName);
			
			return createConversationList(user.getConversations());
		}catch (Exception e) {
			return "{\"error\": \"Something went wrong. Try again later.\"}";
		}		
	}
	@RequestMapping(value = conversation, method = RequestMethod.GET)
	public String getMessages(@RequestParam("convid") BigInteger convId,HttpSession session){
		if(!LoginController.isLoggedIn(session))
			return "{\"url\":\""+LoginController.loginPage+"\"}";
		try{
			Conversation actualConv = convService.getConversation(convId);
			if(actualConv == null) throw new NullPointerException();
				
			//TODO üzeneteket elkérni valahogy. BTW ezt talán MessageControllerbe kéne már rakni
		}catch (NullPointerException e) {
			return "{\"error\":\"Something went wrong. Try again later.\"}";
		}catch (Exception e) {
			return "{\"error\":\"Something went wrong. Try again later.\"}";
		}
		
		
		
		return null;
	}

	private String createConversationList(List<Conversation> conversations) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"conversations\":[");
		
		if(conversations != null)
			if(!conversations.isEmpty()){
				for (int i = 0; i < conversations.size(); i++) {
					Conversation conv = conversations.get(i);
					
					sb.append(getConvInJson(conv.getConversationId().toString(), conv.getTitle(), ""));
					if(i < conversations.size() - 1)
						sb.append(",");
				}
		}		

		sb.append("]}");
		return sb.toString();
	}
	
	private String getConvInJson(String link, String title, String subtitle){
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append("\"convid\":");
		sb.append("\"" + link + "\",");
		sb.append("\"title\":");
		sb.append("\"" + title + "\",");
		sb.append("\"subtitle\":");
		sb.append("\"" + subtitle + "\"");
		sb.append("}");
		return sb.toString();
	}
	
}
