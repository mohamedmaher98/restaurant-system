package com.spring.restaurant.serveices;

import com.spring.restaurant.dto.ProductDTO;
import org.springframework.data.domain.*;

import java.util.*;

public interface ProductService
{
	List<ProductDTO> getAllProducts(Pageable pageable);

	ProductDTO getProductById(UUID id);

	ProductDTO createProduct(ProductDTO productDto);

	ProductDTO updateProduct(UUID id, ProductDTO productDto);

	void deleteProduct(UUID id);

	List<ProductDTO> findByCategoryId(UUID categoryId);

	List<ProductDTO> searchByProductNama(String key);
}
