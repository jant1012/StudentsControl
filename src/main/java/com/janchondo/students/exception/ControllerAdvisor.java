package com.janchondo.students.exception;

import java.util.Date;

import com.janchondo.students.DTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	@ExceptionHandler(StudentNotFoundException.class )
	protected ResponseEntity<ExceptionDTO> handleStudentsNotFound(StudentNotFoundException ex, WebRequest request) {
		ExceptionDTO details = new ExceptionDTO(new Date(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoStudentsFoundException.class )
	protected ResponseEntity<ExceptionDTO> handleNoStudentsFound(NoStudentsFoundException ex, WebRequest request) {
		ExceptionDTO details = new ExceptionDTO(new Date(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.NOT_FOUND);
	}
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ExceptionDTO> handleGlobalException(Exception exception,WebRequest webRequest){
//		ExceptionDTO details = new ExceptionDTO(new Date(),exception.getMessage(), webRequest.getDescription(false));
//		return new ResponseEntity<>(details,HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
