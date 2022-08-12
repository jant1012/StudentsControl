package com.janchondo.students.exception;

public class NoDataFoundException extends RuntimeException {
	public NoDataFoundException() {
        super("No data found");
    }
}
