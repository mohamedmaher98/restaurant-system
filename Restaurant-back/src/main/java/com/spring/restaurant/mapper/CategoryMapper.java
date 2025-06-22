package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.CategoryDTO;
import com.spring.restaurant.entites.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper
{

	CategoryDTO toDTO(Category category);

	@Mapping(target = "products", ignore = true)
	Category toEntity(CategoryDTO categoryDTO);
}
