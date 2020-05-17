package br.com.selecaojava.services.exceptions;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	
	public ObjectNotFoundException(String msg,HttpStatus httpStatus) {
		super(msg);
		this.httpStatus = httpStatus;
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
