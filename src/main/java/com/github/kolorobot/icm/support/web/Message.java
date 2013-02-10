package com.github.kolorobot.icm.support.web;

public class Message {

	public static final String MESSAGE_ATTRIBUTE = "message";
	
	public enum Type {
		ERROR, WARNING, INFO, SUCCESS;
	}

	private final String message;
	private final Type type;

	public Message(String message, Type type) {
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public Type getType() {
		return type;
	}
}
