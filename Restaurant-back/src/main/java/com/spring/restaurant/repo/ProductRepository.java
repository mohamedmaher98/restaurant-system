package com.spring.restaurant.repo;

import com.spring.restaurant.entites.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>
{
	@Query("SELECT p FROM Product p JOIN p.category c WHERE c.id = :categoryId")
	List<Product> findByCategoryId(@Param("categoryId") UUID categoryId);
}
