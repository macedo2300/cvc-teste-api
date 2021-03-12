package com.br.cvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorValidacao  {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ErroCampo handle(MissingServletRequestParameterException exception) {
		
		ErroCampo retorno = new ErroCampo(exception.getParameterName(),exception.getMessage().toString()); ;
		return retorno;
		
	}


	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ErroCampo exception(Exception exception) {

		ErroCampo retorno = new ErroCampo(exception.getCause().toString(),exception.getMessage().toString()); ;
		return retorno;

	}

}
