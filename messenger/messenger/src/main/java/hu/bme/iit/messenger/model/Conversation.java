package hu.bme.iit.messenger.model;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.*;

@Entity(name = "CONVERSTAIONS")
public class Conversation {
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE)
	@Column(nullable=false, unique=true)
	private BigInteger conversationId;
	
	@ManyToMany
	private List<User> members;
	
	@OneToMany
	private List<Message> messages;
	
	public Conversation(){}

	public BigInteger getConversationId() {
		return conversationId;
	}

	public void setConversationId(BigInteger conversationId) {
		this.conversationId = conversationId;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
