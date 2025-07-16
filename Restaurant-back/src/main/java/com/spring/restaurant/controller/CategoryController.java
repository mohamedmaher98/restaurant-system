package com.spring.restaurant.controller;

import com.spring.restaurant.dto.CategoryDTO;
import com.spring.restaurant.serveices.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController
{
	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAllCategories()
	{
		return ResponseEntity.ok(categoryService.getAllCategories());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable UUID id)
	{
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)
	{
		return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable UUID id, @Valid @RequestBody CategoryDTO categoryDTO)
	{
		return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable UUID id)
	{
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
}
