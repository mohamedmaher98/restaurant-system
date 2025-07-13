package com.spring.restaurant.repo;

import com.spring.restaurant.entites.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChefRepository extends JpaRepository<Chef, UUID>
{
}
