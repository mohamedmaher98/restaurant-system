package com.spring.restaurant.serviceImpl;

import com.spring.restaurant.dto.ProductDTO;
import com.spring.restaurant.entites.Product;
import com.spring.restaurant.exception.RestaurantException;
import com.spring.restaurant.mapper.ProductMapper;
import com.spring.restaurant.repo.ProductRepository;
import com.spring.restaurant.serveices.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService
{
	private final ProductRepository repository;
	private final ProductMapper mapper;

	@Override
	public List<ProductDTO> getAllProducts(Pageable pageable)
	{
		return repository.findAll(pageable).getContent().stream().map(mapper::toDto).toList();
	}

	@Override
	public ProductDTO getProductById(UUID id)
	{
		Product product = repository.findById(id).orElseThrow(() -> new RestaurantException("Product not found with id: " + id));
		return mapper.toDto(product);
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO)
	{
		Product product = mapper.toEntity(productDTO);
		Product savedProduct = repository.save(product);
		return mapper.toDto(savedProduct);
	}

	@Override
	public ProductDTO updateProduct(UUID id, ProductDTO updatedProduct)
	{
		Product existingProduct = repository.findById(id).orElseThrow(() -> new RestaurantException("Product not found with id: " + id));
		updateProduct(updatedProduct, existingProduct);
		Product saved = repository.save(existingProduct);
		return mapper.toDto(saved);
	}

	private static void updateProduct(ProductDTO updatedProduct, Product existingProduct)
	{
		existingProduct.setName(updatedProduct.getName());
		existingProduct.setImagePath(updatedProduct.getImagePath());
		existingProduct.setDescription(updatedProduct.getDescription());
		existingProduct.setPrice(updatedProduct.getPrice());
	}

	@Override
	public void deleteProduct(UUID id)
	{
		if (!repository.existsById(id))
			throw new RestaurantException("Product not found with id: " + id);
		repository.deleteById(id);
	}

	@Override
	public List<ProductDTO> findByCategoryId(UUID categoryId)
	{
		return repository.findByCategoryId(categoryId).stream().map(mapper::toDto).toList();
	}

	@Override
	public List<ProductDTO> searchByProductNama(String key)
	{
		return repository.findByNameContainingIgnoreCase(key).stream().map(mapper::toDto).toList();
	}

}
