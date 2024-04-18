package com.eduardo.os.services.exception;

import java.io.Serializable;


public class FiledMessage implements Serializable {
	private static final long serialVersionUID = 6853711600295961158L;
	
	
	private String fieldbame;
	private String message;
	
	public FiledMessage() {
		super();

	}

	public FiledMessage(String fieldbame, String message) {
		super();
		this.fieldbame = fieldbame;
		this.message = message;
	}

	public String getFieldbame() {
		return fieldbame;
	}

	public void setFieldbame(String fieldbame) {
		this.fieldbame = fieldbame;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
