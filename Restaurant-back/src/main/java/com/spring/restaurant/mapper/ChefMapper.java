package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.ChefDTO;
import com.spring.restaurant.entites.Chef;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChefMapper
{
	ChefDTO toDto(Chef chef);

	Chef toEntity(ChefDTO chefDto);
}
