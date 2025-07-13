package com.spring.restaurant.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin
public class ProductDTO extends BaseEntityDTO
{
	@Size(max = 1000, message = "Description is too long")
	private String description;

	@NotNull(message = "Price is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
	private Double price;

	private CategoryDTO category;
}
