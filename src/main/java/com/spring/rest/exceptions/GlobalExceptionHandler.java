package com.spring.rest.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.rest.Controller.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> ResourceNotFoundHandler(ResourceNotFoundException ex){
		
		String message=ex.getMessage();
		ApiResponse apiresponse=new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
		
		
		

	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String , String>> getMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String , String> mapp=new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			
			String fieldName=((FieldError)error).getField();
			String message = error.getDefaultMessage();
			mapp.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(mapp,HttpStatus.BAD_REQUEST);
		
		
	}
	
	
}
