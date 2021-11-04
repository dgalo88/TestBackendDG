package com.dg.testbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6871806099020423394L;

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Long id) {
		super(String.format("id: %d", id));
	}

}
