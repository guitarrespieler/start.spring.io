package hu.bme.iit.messenger.controller.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.bme.iit.messenger.model.Conversation;
import hu.bme.iit.messenger.model.Message;
import hu.bme.iit.messenger.model.repositories.ConversationRepository;

@Service
public class ConversationService {

	@Autowired
	private ConversationRepository conversations;
	
	public List<Conversation> getAllConversation(){
		List<Conversation> list = new LinkedList<>();
		
		Iterable<Conversation> temp = conversations.findAll();
		
		for (Conversation Conversation : temp) {
			list.add(Conversation);
		}
		
		return list;
	}
	
	public Conversation getConversation(Long id){
		return conversations.findOne(id);
	}
	
	public void addConversation(Conversation newConversation){
		conversations.saveAndFlush(newConversation);
	}
	
	public void updateConversation(Conversation Conversation){
		conversations.save(Conversation);
	}
	
	public void deleteConversation(Long id){
		conversations.delete(id);
	}
	public void addMessage(Conversation conv, Message mess){
		conv.getMessages().add(mess);
		conversations.save(conv);
	}
	
}
