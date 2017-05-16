package hu.bme.iit.messenger.model;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;

@Entity
@Table(name="CONVERSATIONS")
public class Conversation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_conversation", joinColumns = @JoinColumn(name = "userid", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "conversationId", referencedColumnName = "id"))
	private List<User> members;
	
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
	private Set<Message> messages = new TreeSet<Message>(new Message());
	
	public Conversation(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
