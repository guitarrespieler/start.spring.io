package hu.bme.iit.messenger.model;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;

@Entity
@Table(name="CONVERSATIONS")
public class Conversation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger conversationId;
	
	private String title;
	
	@ManyToMany
	private List<User> members;
	
	@OneToMany
	private Set<Message> messages = new TreeSet<Message>(new Message());
	
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

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
