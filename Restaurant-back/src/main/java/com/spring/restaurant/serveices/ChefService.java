package com.spring.restaurant.serveices;

import com.spring.restaurant.dto.ChefDTO;

import java.util.*;

public interface ChefService
{
	List<ChefDTO> getAllChefs();

	ChefDTO getChefById(UUID id);

	ChefDTO createChef(ChefDTO chefDTO);

	ChefDTO updateChef(UUID id, ChefDTO chefDTO);

	void deleteChef(UUID id);
}
