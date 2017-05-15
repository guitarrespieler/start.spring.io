package hu.bme.iit.messenger.controller;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import hu.bme.iit.messenger.controller.services.ConversationService;
import hu.bme.iit.messenger.controller.services.MessageService;
import hu.bme.iit.messenger.controller.services.UserService;
import hu.bme.iit.messenger.model.Conversation;
import hu.bme.iit.messenger.model.Message;
import hu.bme.iit.messenger.model.User;

@RestController
public class ConversationsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConversationService convService;
	
	public static final String conversationsPage = "/conversations";
	public static final String convlist = "/getConversations";
	public static final String conversation = "/conversation";

	@RequestMapping(value = conversation, method = RequestMethod.POST)
	public String createConversation(@RequestBody Long userid, HttpSession session){
		if(!LoginController.isLoggedIn(session))
			return "{\"url\":\""+LoginController.loginPage+"\"}";
		try {
			User addressee = userService.getUser(userid);
			User loggedinuser = (User) session.getAttribute(LoginController.userSessionAttribName);
			
			Conversation convers = new Conversation();
			convers.getMembers().add(loggedinuser);
			convers.getMembers().add(addressee);
			
			convers.setTitle("Conversation with: "+ addressee.getFirstName() + " " + addressee.getLastName());
			convService.addConversation(convers);
			
			loggedinuser.getConversations().add(convers);
			addressee.getConversations().add(convers);
			
			userService.updateUser(addressee);
			userService.updateUser(loggedinuser);
			
			return "{\"convid\": " + convers.getConversationId() + "}";
		} catch (Exception e) {
			return "{\"error\":\"Something went wrong. Try again later.\"}";
		}
	}
	
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
				
			User loggedinUser = (User) session.getAttribute(LoginController.userSessionAttribName);
			if(!loggedinUser.getConversations().contains(actualConv))
				throw new NullPointerException();
			
			return jsonifyConversation(actualConv);
			
		}catch (NullPointerException e) {
			return "{\"error\":\"Something went wrong. Try again later.\"}";
		}catch (Exception e) {
			return "{\"error\":\"Something went wrong. Try again later.\"}";
		}
	}

	private String jsonifyConversation(Conversation actualConv) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"convid\": " + actualConv.getConversationId() +",");
		
		sb.append("\"messages\": [");
		
		Iterator<Message> iter = actualConv.getMessages().iterator();
		
		while (iter.hasNext()) {
			Message message = iter.next();
			
			sb.append(jsonifyMessage(message));
			if(iter.hasNext())
				sb.append(",");			
		}
		sb.append("]}");
		
		
		return sb.toString();
	}

	private String jsonifyMessage(Message message) {
		StringBuilder sb = new StringBuilder();		
		sb.append("{");
		sb.append("\"author\": \"" + message.getAuthor() + "\",");
		sb.append("\"content\": \"" + escapeHtml4(message.getContent()) + "\"");
		sb.append("}");	
		return sb.toString();
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
