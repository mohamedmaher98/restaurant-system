package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.CategoryDTO;
import com.spring.restaurant.entites.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper
{
	CategoryDTO toDto(Category category);

	Category toEntity(CategoryDTO categoryDto);
}
