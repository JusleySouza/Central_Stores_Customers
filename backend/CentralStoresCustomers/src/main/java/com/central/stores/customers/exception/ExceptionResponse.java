package com.central.stores.customers.exception;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;

@Getter
public class ExceptionResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Date timestamp;
	private String message;
	private String details;
	
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

}
