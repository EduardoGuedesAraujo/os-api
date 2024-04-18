package com.eduardo.os.services.exception;

import java.util.ArrayList;
import java.util.List;

import com.eduardo.os.resources.exception.StandardError;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	

	private List<FiledMessage> errors = new ArrayList<>();


	public ValidationError() {
		super();
		
	}


	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
		
	}


	public List<FiledMessage> getErrors() {
		return errors;
	}


	public void addError(String fieldbame,String message) {
		this.errors.add(new FiledMessage(fieldbame,message));
	}
	
	
	
}
