package com.github.kolorobot.icm.error;

@SuppressWarnings("serial")
public class AjaxRequestException extends RuntimeException {
	public AjaxRequestException(String message) {
		super(message);
	}
}
