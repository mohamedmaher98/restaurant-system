package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.ProductDTO;
import com.spring.restaurant.entites.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper
{
	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "imagePath", source = "imagePath")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "price", source = "price")
	@Mapping(target = "category", source = "category")
	ProductDTO toDto(Product product);

	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "imagePath", source = "imagePath")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "price", source = "price")
	@Mapping(target = "category", source = "category")
	Product toEntity(ProductDTO productDto);
}
