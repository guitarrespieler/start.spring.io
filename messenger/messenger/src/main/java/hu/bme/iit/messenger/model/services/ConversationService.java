package hu.bme.iit.messenger.model.services;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.bme.iit.messenger.model.Conversation;
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
	
	public Conversation getConversation(BigInteger id){
		return conversations.findOne(id);
	}
	
	public void addConversation(Conversation newConversation){
		conversations.save(newConversation);
	}
	
	public void updateConversation(Conversation Conversation){
		conversations.save(Conversation);
	}
	
	public void deleteConversation(BigInteger id){
		conversations.delete(id);
	}
	
	
}
