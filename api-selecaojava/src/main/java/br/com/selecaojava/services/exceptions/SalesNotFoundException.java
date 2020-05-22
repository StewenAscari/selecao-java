package br.com.selecaojava.services.exceptions;

import org.springframework.http.HttpStatus;

public class SalesNotFoundException extends Exception{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

		public SalesNotFoundException (String mensagem, HttpStatus httpStatus){
	              super (mensagem);
	              this.httpStatus = httpStatus;
	     }

		public HttpStatus getHttpStatus() {
			return httpStatus;
		}
	
		
}
