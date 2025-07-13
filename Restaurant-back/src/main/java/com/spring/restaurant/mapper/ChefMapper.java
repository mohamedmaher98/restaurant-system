package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.ChefDTO;
import com.spring.restaurant.entites.Chef;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChefMapper
{
	ChefMapper INSTANCE = Mappers.getMapper(ChefMapper.class);

	ChefDTO toDto(Chef chef);

	Chef toEntity(ChefDTO chefDto);
}
