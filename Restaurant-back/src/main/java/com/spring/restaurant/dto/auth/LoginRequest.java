package com.spring.restaurant.dto.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest
{
	private String usernameOrEmail;
	private String password;
}
