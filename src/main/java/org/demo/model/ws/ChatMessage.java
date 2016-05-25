package org.demo.model.ws;

/**
 * @author Sebastian Börebäck on 2016-05-17.
 * Chat message, model used for chating with users
 */
public class ChatMessage {
	String sender;
	String message;


	public ChatMessage() {
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
