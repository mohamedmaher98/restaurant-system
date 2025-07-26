package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.ChefDTO;
import com.spring.restaurant.entites.Chef;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChefMapper
{
	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "imagePath", source = "imagePath")
	@Mapping(target = "specialty", source = "specialty")
	@Mapping(target = "facebookLink", source = "facebookLink")
	@Mapping(target = "twitterLink", source = "twitterLink")
	@Mapping(target = "instagramLink", source = "instagramLink")
	ChefDTO toDto(Chef chef);

	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "imagePath", source = "imagePath")
	@Mapping(target = "specialty", source = "specialty")
	@Mapping(target = "facebookLink", source = "facebookLink")
	@Mapping(target = "twitterLink", source = "twitterLink")
	@Mapping(target = "instagramLink", source = "instagramLink")
	Chef toEntity(ChefDTO chefDto);
}
