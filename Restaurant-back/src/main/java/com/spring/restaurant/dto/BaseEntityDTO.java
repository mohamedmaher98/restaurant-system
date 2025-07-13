package com.spring.restaurant.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
public class BaseEntityDTO
{
	private UUID id;

	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Name must be less than 100 characters")
	private String name;

	@Size(max = 255, message = "Logo is too long")
	private String imagePath;
}
