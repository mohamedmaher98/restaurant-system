package com.spring.restaurant.dto.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest
{
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
}
