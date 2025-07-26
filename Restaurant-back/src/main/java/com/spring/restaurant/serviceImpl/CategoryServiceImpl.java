package com.spring.restaurant.serviceImpl;

import com.spring.restaurant.dto.CategoryDTO;
import com.spring.restaurant.entites.Category;
import com.spring.restaurant.exception.RestaurantException;
import com.spring.restaurant.mapper.CategoryMapper;
import com.spring.restaurant.repo.CategoryRepository;
import com.spring.restaurant.serveices.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService
{
	private final CategoryRepository repository;

	private final CategoryMapper mapper;

	@Override
	public List<CategoryDTO> getAllCategories()
	{
		return repository.findAllByOrderByNameAsc().stream().map(mapper::toDto).toList();
	}

	@Override
	public CategoryDTO getCategoryById(UUID id)
	{
		return mapper.toDto(repository.findById(id).orElseThrow(() -> new RestaurantException("No Category Found with id: " + id)));
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categorydto)
	{
		Category category = repository.save(mapper.toEntity(categorydto));
		return mapper.toDto(category);
	}

	@Override
	public CategoryDTO updateCategory(UUID id, CategoryDTO updatedCategory)
	{
		Category category = repository.findById(id).orElseThrow(() -> new RestaurantException("No Category Found"));
		updateCategory(updatedCategory, category);
		return mapper.toDto(repository.save(category));
	}

	private static void updateCategory(CategoryDTO updatedCategory, Category category)
	{
		category.setName(updatedCategory.getName());
		category.setFlag(updatedCategory.getFlag());
		category.setImagePath(updatedCategory.getImagePath());
	}

	@Override
	public void deleteCategory(UUID id)
	{
		if (!repository.existsById(id))
			throw new RestaurantException("Category not found with id: " + id);
		repository.deleteById(id);
	}
}
