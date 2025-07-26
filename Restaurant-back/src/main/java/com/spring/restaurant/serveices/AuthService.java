package com.spring.restaurant.serveices;

import com.spring.restaurant.dto.auth.*;
import org.springframework.http.ResponseEntity;

public interface AuthService
{
	ResponseEntity<AuthResponse> register(RegisterRequest registerRequest);

	ResponseEntity<AuthResponse> login(LoginRequest loginRequest);
}
