package com.spring.restaurant.controller;

import com.spring.restaurant.dto.ProductDTO;
import com.spring.restaurant.serveices.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:4200")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAll() {
		return ResponseEntity.ok(productService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getById(@PathVariable UUID id) {
		return ResponseEntity.ok(productService.getById(id));
	}

	@PostMapping
	public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.save(productDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable UUID id, @Valid @RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.update(id, productDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
}