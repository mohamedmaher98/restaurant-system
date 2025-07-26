package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.CategoryDTO;
import com.spring.restaurant.entites.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper
{
	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "imagePath", source = "imagePath")
	@Mapping(target = "flag", source = "flag")
	CategoryDTO toDto(Category category);

	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "imagePath", source = "imagePath")
	@Mapping(target = "flag", source = "flag")
	@Mapping(target = "products", ignore = true)
	Category toEntity(CategoryDTO categoryDto);
}
