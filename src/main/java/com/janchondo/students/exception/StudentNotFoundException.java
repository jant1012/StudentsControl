package com.janchondo.students.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class StudentNotFoundException extends RuntimeException {
	private HttpStatus state;
	private String message;
	public StudentNotFoundException(HttpStatus state, String message) {
		this.state = state;
		this.message = message;
	}
}
