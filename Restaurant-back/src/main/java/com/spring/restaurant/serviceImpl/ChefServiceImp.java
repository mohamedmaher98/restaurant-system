package com.spring.restaurant.serviceImpl;

import com.spring.restaurant.dto.ChefDTO;
import com.spring.restaurant.entites.Chef;
import com.spring.restaurant.exception.RestaurantException;
import com.spring.restaurant.mapper.ChefMapper;
import com.spring.restaurant.repo.ChefRepository;
import com.spring.restaurant.serveices.ChefService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChefServiceImp implements ChefService
{
	private final ChefRepository repository;
	private final ChefMapper mapper;

	@Override
	public List<ChefDTO> getAllChefs()
	{
		return repository.findAll().stream().map(mapper::toDto).toList();
	}

	@Override
	public ChefDTO getChefById(UUID id)
	{
		return mapper.toDto(repository.findById(id).orElseThrow(() -> new RestaurantException("Chef not found with id: " + id)));
	}

	@Override
	public ChefDTO createChef(ChefDTO chefDTO)
	{
		return mapper.toDto(repository.save(mapper.toEntity(chefDTO)));
	}

	@Override
	public ChefDTO updateChef(UUID id, ChefDTO chefDTO)
	{
		Chef existingChef = repository.findById(id).orElseThrow(() -> new RestaurantException("Chef not found with id: " + id));
		Chef updatedChef = mapper.toEntity(chefDTO);
		updateChef(existingChef, updatedChef);
		return mapper.toDto(repository.save(updatedChef));
	}

	private void updateChef(Chef existingChef, Chef updatedChef)
	{
		existingChef.setName(updatedChef.getName());
		existingChef.setSpecialty(updatedChef.getSpecialty());
		existingChef.setImagePath(updatedChef.getImagePath());
		existingChef.setFacebookLink(updatedChef.getFacebookLink());
		existingChef.setInstagramLink(updatedChef.getInstagramLink());
		existingChef.setTwitterLink(updatedChef.getTwitterLink());
	}

	@Override
	public void deleteChef(UUID id)
	{
		if (!repository.existsById(id))
			throw new RestaurantException("Chef not found with id: " + id);
		repository.deleteById(id);
	}
}
