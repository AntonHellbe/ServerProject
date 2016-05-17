package org.demo.model.ws;

/**
 * Created by Sebastian Börebäck on 2016-05-17.
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
