package com.spring.restaurant.repo;

import com.spring.restaurant.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>
{
	List<Category> findAllByOrderByNameAsc();
}
