package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.apiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<apiResponse> resourcenotfounrexceptionhandler(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		apiResponse  apiResponse=new apiResponse(message, false);
		return new ResponseEntity<apiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException ex)
	{
		Map<String, String> map=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			
			String fieldname = ((FieldError)error).getField();
			String message=error.getDefaultMessage();
			map.put(fieldname, message);
		});
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<apiResponse> apiException(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		apiResponse  apiResponse=new apiResponse(message, true);
		return new ResponseEntity<apiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}

}
