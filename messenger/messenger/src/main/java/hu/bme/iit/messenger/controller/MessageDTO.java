package hu.bme.iit.messenger.controller;

public class MessageDTO {
	private Long conversation;
	
	private String content;

	public Long getConversation() {
		return conversation;
	}

	public void setConversation(Long conversation) {
		this.conversation = conversation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
