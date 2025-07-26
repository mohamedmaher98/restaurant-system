package com.spring.restaurant.dto.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse
{
	private String token;
	private String username;
	private String password;
	private String message;
	private boolean success;
}
