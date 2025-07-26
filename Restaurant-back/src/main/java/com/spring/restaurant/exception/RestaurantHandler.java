package com.spring.restaurant.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class RestaurantHandler
{
	@ExceptionHandler(RestaurantException.class)
	public ResponseEntity<Object> handleResourceNotFound(RestaurantException ex)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
}
