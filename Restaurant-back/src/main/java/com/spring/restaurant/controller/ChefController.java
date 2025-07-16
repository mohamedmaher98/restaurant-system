package com.spring.restaurant.controller;

import com.spring.restaurant.dto.ChefDTO;
import com.spring.restaurant.serveices.ChefService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chefs")
public class ChefController
{
	private final ChefService chefService;

	@GetMapping
	public ResponseEntity<List<ChefDTO>> getAllChefs()
	{
		return ResponseEntity.ok(chefService.getAllChefs());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ChefDTO> getChefById(@PathVariable UUID id)
	{
		return ResponseEntity.ok(chefService.getChefById(id));
	}

	@PostMapping
	public ResponseEntity<ChefDTO> createChef(@Valid @RequestBody ChefDTO ChefDTO)
	{
		return ResponseEntity.ok(chefService.createChef(ChefDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ChefDTO> updateChef(@PathVariable UUID id, @Valid @RequestBody ChefDTO ChefDTO)
	{
		return ResponseEntity.ok(chefService.updateChef(id, ChefDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteChef(@PathVariable UUID id)
	{
		chefService.deleteChef(id);
		return ResponseEntity.noContent().build();
	}
}
