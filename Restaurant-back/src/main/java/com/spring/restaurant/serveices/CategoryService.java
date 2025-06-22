package com.spring.restaurant.serveices;

import com.spring.restaurant.dto.CategoryDTO;
import com.spring.restaurant.entites.Category;

import java.util.*;

public interface CategoryService
{

	List<CategoryDTO> getAll();

	CategoryDTO getById(UUID id);

	CategoryDTO save(CategoryDTO category);

	CategoryDTO update(UUID id, CategoryDTO category);

	void delete(UUID id);
}

