package hu.bme.iit.messenger.model;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="MESSAGES")
public class Message implements Comparator<Message>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger messageId;
	
	@ManyToOne
	private User author;
	
	@ManyToOne
	private Conversation conversation;
	
	private Date timeOfCreation;
	
	private String content;
	
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

	@Override
	public int compare(Message o1, Message o2) {
		int val = o1.getTimeOfCreation().compareTo(o2.getTimeOfCreation());
		
		if(val < 0) return -1;
		if(val > 0) return 1;
		return 0;
			
	}

}
