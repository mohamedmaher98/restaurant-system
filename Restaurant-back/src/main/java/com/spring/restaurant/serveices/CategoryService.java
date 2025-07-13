package com.spring.restaurant.serveices;

import com.spring.restaurant.dto.CategoryDTO;

import java.util.*;

public interface CategoryService
{
	List<CategoryDTO> getAllCategories();

	CategoryDTO getCategoryById(UUID id);

	CategoryDTO createCategory(CategoryDTO category);

	CategoryDTO updateCategory(UUID id, CategoryDTO category);

	void deleteCategory(UUID id);
}

