package org.demo.model.ws;

import java.util.Date;

/**
 * @author Sebastian Börebäck on 2016-05-17.
 * Chat answer, model used for chating with users
 */
public class ChatAnswer {
	String sender;
	String message;
	Date stamp;

	public ChatAnswer() {
		stamp = new Date();
	}

	public ChatAnswer(ChatMessage message) {
		this.sender = message.getSender();
		this.message = message.getMessage();
		stamp = new Date();
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

	public Date getStamp() {
		return stamp;
	}

	@Override
	public String toString() {
		return "ChatAnswer{" +
				"sender='" + sender + '\'' +
				", message='" + message + '\'' +
				", stamp=" + stamp +
				'}';
	}
}
