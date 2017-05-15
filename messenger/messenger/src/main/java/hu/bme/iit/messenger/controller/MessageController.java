package hu.bme.iit.messenger.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.iit.messenger.controller.services.ConversationService;
import hu.bme.iit.messenger.controller.services.MessageService;
import hu.bme.iit.messenger.model.Conversation;
import hu.bme.iit.messenger.model.Message;
import hu.bme.iit.messenger.model.User;

@RestController
public class MessageController {

	public static final String sendMessage = "/sendmessage";
	
	@Autowired
	ConversationService convService;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value=sendMessage, method = RequestMethod.POST)
	public String sendMessage(@RequestBody MessageDTO messagedto, HttpSession session){
		if(!LoginController.isLoggedIn(session))
			return "{\"url\":\""+LoginController.loginPage+"\"}";
		try {
			User loggedinuser = (User) session.getAttribute(LoginController.userSessionAttribName);
			
			Date currentdate = Calendar.getInstance().getTime();
			
			Message message = new Message();
			
			message.setAuthor(loggedinuser);
			message.setTimeOfCreation(currentdate);
			message.setContent(messagedto.getContent());
			
			
			Conversation conv = convService.getConversation(messagedto.getConversation());
			message.setConversation(conv);
			messageService.addMessage(message);
			conv.getMessages().add(message);
			
			return "{\"status\": \"OK\"}";
		} catch (Exception e) {
			return "{\"error\":\"Something went wrong. Try again later.\"}";
		}
	}
	
}
