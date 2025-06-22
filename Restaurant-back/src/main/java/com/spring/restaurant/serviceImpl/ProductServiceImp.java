package com.spring.restaurant.serviceImpl;

import com.spring.restaurant.dto.ProductDTO;
import com.spring.restaurant.entites.Product;
import com.spring.restaurant.mapper.ProductMapper;
import com.spring.restaurant.repo.ProductRepository;
import com.spring.restaurant.serveices.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImp implements ProductService
{
	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<ProductDTO> getAll() {
		return repository.findAll()
				.stream()
				.map(productMapper::toDto)
				.toList();
	}

	@Override
	public ProductDTO getById(UUID id) {
		Product product = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		return productMapper.toDto(product);
	}

	@Override
	public ProductDTO save(ProductDTO productDTO) {
		Product product = productMapper.toEntity(productDTO);
		Product savedProduct = repository.save(product);
		return productMapper.toDto(savedProduct);
	}

	@Override
	public ProductDTO update(UUID id, ProductDTO updatedProduct) {
		Product existingProduct = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		existingProduct.setName(updatedProduct.name());
		existingProduct.setImagePath(updatedProduct.imagePath());
		existingProduct.setDescription(updatedProduct.description());
		existingProduct.setPrice(updatedProduct.price());

		Product saved = repository.save(existingProduct);
		return productMapper.toDto(saved);
	}

	@Override
	public void delete(UUID id) {
		Product product = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		repository.delete(product);
	}
}
