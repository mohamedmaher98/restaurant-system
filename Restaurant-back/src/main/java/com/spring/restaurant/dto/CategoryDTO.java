package com.spring.restaurant.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO extends BaseEntityDTO
{
	private String flag;
}
