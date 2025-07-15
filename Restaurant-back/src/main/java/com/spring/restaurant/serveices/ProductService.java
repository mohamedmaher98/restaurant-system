package com.spring.restaurant.serveices;

import com.spring.restaurant.dto.ProductDTO;

import java.util.*;

public interface ProductService
{
	List<ProductDTO> getAllProducts();

	ProductDTO getProductById(UUID id);

	ProductDTO createProduct(ProductDTO productDto);

	ProductDTO updateProduct(UUID id, ProductDTO productDto);

	void deleteProduct(UUID id);

	List<ProductDTO> findByCategoryIdHex(UUID categoryId);
}
