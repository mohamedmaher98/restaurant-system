package com.spring.restaurant.controller;

import com.spring.restaurant.dto.ProductDTO;
import com.spring.restaurant.serveices.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController
{
	private final ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize)
	{
		if (pageNo <= 0)
		{
			throw new RuntimeException("Page Number starts with 1");
		}
		return ResponseEntity.ok(productService.getAllProducts(PageRequest.of(pageNo, pageSize)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.createProduct(productDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.updateProduct(id, productDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/findProductsByCategoryId/{categoryId}")
	public ResponseEntity<List<ProductDTO>> fetchProductsByCategoryId(@PathVariable UUID categoryId)
	{
		return ResponseEntity.ok(productService.findByCategoryId(categoryId));
	}

	@GetMapping("/search")
	public ResponseEntity<List<ProductDTO>> searchByProductNama(@RequestParam String key)
	{
		return ResponseEntity.ok(productService.searchByProductNama(key));
	}
}
