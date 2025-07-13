package com.spring.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin
public class ChefDTO extends BaseEntityDTO
{
	@NotBlank(message = "Specialty is required")
	String specialty;

	String facebookLink;
	String twitterLink;
	String instagramLink;
}
