package com.spring.restaurant.serviceImpl;

import com.spring.restaurant.dto.CategoryDTO;
import com.spring.restaurant.entites.Category;
import com.spring.restaurant.mapper.CategoryMapper;
import com.spring.restaurant.repo.CategoryRepository;
import com.spring.restaurant.serveices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepository repository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<CategoryDTO> getAll()
	{
		return repository.findAll().stream().map(categoryMapper::toDTO).toList();
	}

	@Override
	public CategoryDTO getById(UUID id)
	{
		Category category = repository.findById(id).orElseThrow(() -> new RuntimeException("No Category Found"));
		return categoryMapper.toDTO(category);
	}

	@Override
	public CategoryDTO save(CategoryDTO categorydto)
	{
		Category category = categoryMapper.toEntity(categorydto);
		Category savedCategory =repository.save(category);
		return categoryMapper.toDTO(savedCategory);
	}

	@Override
	public CategoryDTO update(UUID id, CategoryDTO updatedCategory)
	{
		Category category= repository.findById(id).orElseThrow(()->new RuntimeException("No Category Found"));
		category.setName(updatedCategory.getName());
		category.setFlag(updatedCategory.getFlag());
		category.setLogo(updatedCategory.getLogo());
		repository.save(category);
		return categoryMapper.toDTO(category);
	}

	@Override
	public void delete(UUID id)
	{
		Category category = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found"));
		repository.deleteById(id);
	}
}
