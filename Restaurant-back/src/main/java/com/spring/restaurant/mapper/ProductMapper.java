package com.spring.restaurant.mapper;

import com.spring.restaurant.dto.ProductDTO;
import com.spring.restaurant.entites.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper
{

	ProductDTO toDto(Product Product);

	@Mapping(target = "category" ,ignore = true)
	Product toEntity(ProductDTO productDTO);

}
