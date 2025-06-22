package com.spring.restaurant.serveices;

import com.spring.restaurant.dto.ProductDTO;
import com.spring.restaurant.entites.Product;

import java.util.*;

public interface ProductService
{
	List<ProductDTO> getAll();

	ProductDTO getById(UUID id);

	ProductDTO save(ProductDTO productDto);

	ProductDTO update(UUID id, ProductDTO productDto);

	void delete(UUID id);
}
