package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.CategoryDTO;
import com.spring.restaurant.entites.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper
{
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	CategoryDTO toDto(Category category);

	Category toEntity(CategoryDTO categoryDto);
}
