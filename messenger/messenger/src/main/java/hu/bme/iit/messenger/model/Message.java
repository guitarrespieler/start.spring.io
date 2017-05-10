package hu.bme.iit.messenger.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.*;

import hu.bme.iit.messenger.model.enums.MessageStatus;

@Entity
public class Message {
	
	@Id
	private BigInteger messageId;
	
	@ManyToOne
	private User author;
	
	@ManyToOne
	private Conversation conversation;
	
	private Date timeOfCreation;
	
	private String content;
	
	private MessageStatus status;
	
	public Message(){}

	public BigInteger getMessageId() {
		return messageId;
	}

	public void setMessageId(BigInteger messageId) {
		this.messageId = messageId;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Date getTimeOfCreation() {
		return timeOfCreation;
	}

	public void setTimeOfCreation(Date timeOfCreation) {
		this.timeOfCreation = timeOfCreation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

}
