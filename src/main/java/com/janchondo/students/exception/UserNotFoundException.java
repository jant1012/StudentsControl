package com.janchondo.students.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String id) {
		super(String.format("User with ID %f not found", id));
	}
}
