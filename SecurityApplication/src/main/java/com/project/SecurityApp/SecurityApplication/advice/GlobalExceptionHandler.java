package com.project.SecurityApp.SecurityApplication.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;

import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception){
		ApiError apiError=new ApiError(exception.getLocalizedMessage(),HttpStatus.NOT_FOUND);
	return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
	}
	  @ExceptionHandler(AuthenticationException.class)
	    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
	        ApiError apiError = new ApiError(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
	        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
	    }
	  @ExceptionHandler(JwtException.class)
	    public ResponseEntity<ApiError> handleJwtException(JwtException ex) {
	        ApiError apiError = new ApiError(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
	        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
	    }
	  
	   @ExceptionHandler(AccessDeniedException.class)
	    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
	        ApiError apiError = new ApiError(ex.getLocalizedMessage(), HttpStatus.FORBIDDEN);
	        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
	    }

}
