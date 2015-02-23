package org.mtdev.regataiades.services;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler({ MissingServletRequestParameterException.class,
			NoSuchRequestHandlingMethodException.class,
			HttpRequestMethodNotSupportedException.class,
			AccessDeniedException.class, BadCredentialsException.class,
			Exception.class })
	public @ResponseBody SystemMessage handleAllException(Exception ex) {

		SystemMessage lError = new SystemMessage("error", ex.getClass()
				.getSimpleName(), ex.getMessage());

		return lError;

	}

}
