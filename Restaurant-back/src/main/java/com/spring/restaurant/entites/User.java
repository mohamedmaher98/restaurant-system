package com.spring.restaurant.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity
{
	private String password;

	@Column(nullable = false, unique = true)
	private String username;
	private String email;
	private String phone;
}
