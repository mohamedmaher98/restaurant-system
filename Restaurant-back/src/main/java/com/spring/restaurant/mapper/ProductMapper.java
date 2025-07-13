package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.ProductDTO;
import com.spring.restaurant.entites.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper
{
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	ProductDTO toDto(Product product);

	Product toEntity(ProductDTO productDto);
}
