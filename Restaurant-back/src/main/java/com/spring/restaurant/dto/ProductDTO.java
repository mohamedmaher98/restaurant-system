package com.spring.restaurant.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record ProductDTO(
		UUID id,

		@NotBlank(message = "Name is required")
		@Size(max = 100, message = "Name must be less than 100 characters")
		String name,

		@Size(max = 255, message = "Image path is too long")
		String imagePath,

		@Size(max = 1000, message = "Description is too long")
		String description,

		@NotNull(message = "Price is required")
		@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
		Double price,

		@NotNull(message = "Category ID is required")
		Long categoryId,

		String categoryName
)
{
}
