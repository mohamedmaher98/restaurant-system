package com.spring.restaurant.controller;

import com.spring.restaurant.dto.auth.*;
import com.spring.restaurant.serveices.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest)
	{
		return authService.register(registerRequest);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
	{
		return authService.login(loginRequest);
	}
}
