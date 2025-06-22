package com.spring.restaurant.controller;

import com.spring.restaurant.dto.CategoryDTO;
import com.spring.restaurant.serveices.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAll() {
		return ResponseEntity.ok(categoryService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getById(@PathVariable UUID id) {
		return ResponseEntity.ok(categoryService.getById(id));
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO) {
		return ResponseEntity.ok(categoryService.save(categoryDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable UUID id, @Valid @RequestBody CategoryDTO categoryDTO) {
		return ResponseEntity.ok(categoryService.update(id, categoryDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
}