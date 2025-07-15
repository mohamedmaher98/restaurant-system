package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.ProductDTO;
import com.spring.restaurant.entites.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper
{
	ProductDTO toDto(Product product);

	Product toEntity(ProductDTO productDto);
}
