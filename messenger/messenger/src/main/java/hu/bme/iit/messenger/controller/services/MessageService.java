package hu.bme.iit.messenger.controller.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.bme.iit.messenger.model.Message;
import hu.bme.iit.messenger.model.repositories.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messages;
	
	public List<Message> getAllMessage(){
		List<Message> list = new LinkedList<>();
		
		Iterable<Message> temp = messages.findAll();
		
		for (Message Message : temp) {
			list.add(Message);
		}
		
		return list;
	}
	
	public Message getMessage(Long id){
		return messages.findOne(id);
	}
	
	public void addMessage(Message newMessage){
		messages.save(newMessage);
	}
	
	public void updateMessage(Message Message){
		messages.save(Message);
	}
	
	public void deleteMessage(Long id){
		messages.delete(id);
	}
}
