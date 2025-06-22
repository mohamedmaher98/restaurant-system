package com.spring.restaurant.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
public class CategoryDTO {
	private UUID id;

	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Name must be less than 100 characters")
	private String name;

	@Size(max = 255, message = "Logo is too long")
	private String logo;

	private String flag;
}
